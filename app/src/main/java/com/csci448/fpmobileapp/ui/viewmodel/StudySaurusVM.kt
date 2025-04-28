package com.csci448.fpmobileapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.fpmobileapp.data.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.data.ShopItem
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus, private val taskDao: TaskDao, private val itemsDao: ItemsDao) : ViewModel() {
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf(mySaurus)

    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.STARTUP)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }

    val taskList: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    val totalCoins: StateFlow<Int> = taskList
        .map { tasks ->
            tasks.filter { it.completed }.sumOf { it.coins }
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, 0)

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

    fun deleteCompletedTasks() {
        viewModelScope.launch {
            taskDao.deleteAllCompleted()
        }
    }

    val allItems: StateFlow<List<ShopItem>> = itemsDao.getAllItems()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Optionally, you can also define a flow for shop items only:
    val shopItems: StateFlow<List<ShopItem>> = allItems
        .map { list -> list.filter { !it.owned } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    fun insertItem(item: ShopItem) {
        viewModelScope.launch {
            if (!allItems.value.contains(item)){
                itemsDao.insertItem(item)
            }
        }
    }

    // Owned items flow:
    val ownedItems: StateFlow<List<ShopItem>> = allItems
        .map { list -> list.filter { it.owned } }
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

    // Purchase items => set "owned = true" and update DB
    fun purchaseItems(itemsToBuy: List<ShopItem>) {
        viewModelScope.launch {
            itemsToBuy.forEach { item ->
                // Mark the item as owned
                itemsDao.updateItem(item.copy(owned = true))
            }
        }
    }


}

