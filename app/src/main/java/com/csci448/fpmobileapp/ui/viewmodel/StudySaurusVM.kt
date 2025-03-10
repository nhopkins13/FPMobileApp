package com.csci448.fpmobileapp.ui.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.SelectedScreen

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
class StudySaurusVM(private val mySaurus: Saurus) : ViewModel() {
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf(mySaurus)

    val currentScreen: MutableState<SelectedScreen> = mutableStateOf(SelectedScreen.NONE)

    fun setCurrentScreen(screenSelection: SelectedScreen){
        currentScreen.value = screenSelection
    }
}

