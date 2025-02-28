package com.csci448.fpmobileapp.ui.viewmodel

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.csci448.fpmobileapp.data.Saurus

/**
 * the ViewModel for our app
 *
 * TODO:
 *  the whole thing
 */
data class StudySaurusVM(private val mySaurus: Saurus) : ViewModel(){
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf( mySaurus )
}