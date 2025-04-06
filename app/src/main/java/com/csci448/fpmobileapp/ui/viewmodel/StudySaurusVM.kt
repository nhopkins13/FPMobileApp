package com.csci448.fpmobileapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SelectedScreen
import com.csci448.fpmobileapp.data.Task

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus) : ViewModel() {
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf(mySaurus)

    val taskList: MutableList<Task> = mutableListOf()

    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.STARTUP)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }

    fun addTask(task: Task){
        taskList.plus(task)
    }
}

