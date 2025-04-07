package com.csci448.fpmobileapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.data.Task
import com.csci448.fpmobileapp.data.TaskDao
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus, private val taskDao: TaskDao) : ViewModel() {
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf(mySaurus)

    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.STARTUP)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }

    val taskList: StateFlow<List<Task>> = taskDao.getAllTasks()
        .stateIn(viewModelScope, SharingStarted.Eagerly, emptyList())

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


}

