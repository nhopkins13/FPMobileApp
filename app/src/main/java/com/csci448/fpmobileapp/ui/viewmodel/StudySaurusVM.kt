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
class StudySaurusVM(private val mySaurus: Saurus) : ViewModel() {
    val currentSaurusState: State<Saurus>
        get() = mutableStateOf(mySaurus)


    fun goToLogin() {

    }

    fun goToSignUp() {

    }

    fun goToHome() {

    }

    fun goToWardrobe() {

    }

    fun goToTask() {

    }

    fun goToSocial() {

    }

    fun goToShop() {

    }

    fun goToSetting() {

    }
}

