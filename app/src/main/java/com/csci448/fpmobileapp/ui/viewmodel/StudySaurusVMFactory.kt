package com.csci448.fpmobileapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.fpmobileapp.data.repos.AuthRepo
import com.csci448.fpmobileapp.data.repos.UserRepo
import com.csci448.fpmobileapp.data.daos.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.repos.SaurusSettingsRepo
import com.csci448.fpmobileapp.data.daos.TaskDao

class StudySaurusVMFactory(
    private val mySaurus: Saurus,
    private val taskDao: TaskDao,
    private val itemsDao: ItemsDao,
    private val saurusSettingsRepository: SaurusSettingsRepo,
    private val authRepository: AuthRepo,
    private val userRepository: UserRepo
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudySaurusVM(mySaurus, taskDao, itemsDao, saurusSettingsRepository, authRepository, userRepository) as T
    }
}

