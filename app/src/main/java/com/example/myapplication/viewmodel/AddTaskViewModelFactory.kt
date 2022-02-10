package com.example.myapplication.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.database.AppDatabase

class AddTaskViewModelFactory
    (
    private val mDb: AppDatabase, private val mTaskId: Int
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return AddTaskViewModel(mDb, mTaskId) as T
    }
}