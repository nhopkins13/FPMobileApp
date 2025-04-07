package com.csci448.fpmobileapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.csci448.fpmobileapp.data.Saurus
import com.csci448.fpmobileapp.data.TaskDao

class StudySaurusVMFactory(private val saurus: Saurus, private val dao: TaskDao) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return StudySaurusVM(saurus, dao) as T
    }
}
