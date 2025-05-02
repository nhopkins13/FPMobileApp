package com.csci448.fpmobileapp.ui.viewmodel

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.fpmobileapp.data.ItemRepo
import com.csci448.fpmobileapp.data.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.data.TaskDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus, private val taskDao: TaskDao, private val itemsDao: ItemsDao) : ViewModel() {
    private val _currentSaurus = mutableStateOf(mySaurus.copy())
    val currentSaurusState: State<Saurus> = _currentSaurus

    init {
        viewModelScope.launch {
            // only insert if there are no items yet
            val existing = itemsDao.getAllItemsOnce()
            if (existing.isEmpty()) {
                itemsDao.insertItem(ItemRepo.topHat)
            }
        }
    }


    fun selectHat(id: Int) {
        _currentSaurus.value = _currentSaurus.value.copy(hat = id)
    }
    fun selectNeckwear(id: Int) {
        _currentSaurus.value = _currentSaurus.value.copy(neckWear = id)
    }
    fun selectBelt(id: Int) {
        _currentSaurus.value = _currentSaurus.value.copy(belt = id)
    }


    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.STARTUP)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }

    val taskList: StateFlow<List<Task>> = taskDao
        .getAllTasks()
        .map { list -> list.filter { !it.archived } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val totalCoins: StateFlow<Int> = taskList
        .map { tasks -> tasks.filter { it.completed }.sumOf { it.coins } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

    val archivedTasks: StateFlow<List<Task>> = taskDao
        .getArchivedTasks()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun archiveCompletedTasks() {
        viewModelScope.launch {
            taskDao.archiveCompletedTasks()
        }
    }

    fun addTask(task: Task) {
        viewModelScope.launch {
            taskDao.insertTask(task)
        }
    }

    fun updateTask(task: Task) {
        viewModelScope.launch {
            taskDao.updateTask(task)
        }
    }

    val allItems: StateFlow<List<ShopItem>> = itemsDao.getAllItems()
        .map { list ->
            // Log the raw list from the DAO
            Log.d("DATA_FLOW_DEBUG", "allItems Flow Emitted (Raw from DAO): ${list.size} items -> ${list.joinToString { it.name + "(id=${it.id}, owned=${it.owned})" }}")
            list // return original list
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Optionally, you can also define a flow for shop items only:
    val shopItems: StateFlow<List<ShopItem>> = allItems
        .map { list -> list.filter { !it.owned } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertShopItem(item: ShopItem) {
        viewModelScope.launch {
            itemsDao.insertItem(item)
        }
    }

    // Owned items flow:
    val ownedItems: StateFlow<List<ShopItem>> = allItems
        .map { list ->
            // Log before filtering for owned
            Log.d("DATA_FLOW_DEBUG", "ownedItems Flow: Filtering ${list.size} items for owned status.")
            list.filter { it.owned }
        }
        .map { ownedList ->
            // Log the result after filtering
            Log.d("DATA_FLOW_DEBUG", "ownedItems Flow Emitted (Filtered): ${ownedList.size} items -> ${ownedList.joinToString { it.name + "(id=${it.id})" }}")
            ownedList
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    //Shop Logic
    private val _coinsSpent = MutableStateFlow(0)

    val availableCoins = totalCoins
        .combine(_coinsSpent) { earned, spent -> earned - spent }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)



    fun purchaseItems(itemsToBuy: List<ShopItem>) {
        viewModelScope.launch {
            Log.d("DATA_FLOW_DEBUG", "----- Purchase Start -----")
            Log.d(
                "DATA_FLOW_DEBUG",
                "Attempting to purchase: ${itemsToBuy.joinToString { it.name + "(id=${it.id})" }}"
            )
            itemsToBuy.forEach { item ->
                val itemToUpdate = item.copy(owned = true)
                Log.d(
                    "DATA_FLOW_DEBUG",
                    "Calling itemsDao.updateItem for: ${itemToUpdate.name} (id=${itemToUpdate.id}, owned=${itemToUpdate.owned})"
                )
                itemsDao.updateItem(itemToUpdate) // Use the copied item
            }
            val cost = itemsToBuy.sumOf { it.price }
            _coinsSpent.update { it + cost }
            Log.d("DATA_FLOW_DEBUG", "Purchase complete. Cost: $cost")
            Log.d("DATA_FLOW_DEBUG", "----- Purchase End -----")
        }
    }


}

