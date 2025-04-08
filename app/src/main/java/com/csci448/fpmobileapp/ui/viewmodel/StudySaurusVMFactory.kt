package com.csci448.fpmobileapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.fpmobileapp.data.ItemsDao
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.TaskDao

class StudySaurusVMFactory(
    private val mySaurus: Saurus,
    private val taskDao: TaskDao,
    private val itemsDao: ItemsDao
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudySaurusVM(mySaurus, taskDao, itemsDao) as T
    }
}

