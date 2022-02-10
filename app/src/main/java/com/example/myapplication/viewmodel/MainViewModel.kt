package com.example.myapplication.viewmodel

import android.app.Application
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.TaskEntry
import com.example.myapplication.database.TasksRepository

class MainViewModel(application: Application?) : AndroidViewModel(
    application!!
) {
    val tasks: LiveData<List<TaskEntry?>?>?
    private val tasksRepository: TasksRepository
    fun deleteTask(taskEntry: TaskEntry?) {
        tasksRepository.deleteTasks(taskEntry)
    }

    companion object {
        private val TAG = MainViewModel::class.java.simpleName
    }

    init {
        val database = AppDatabase.getInstance(getApplication())
        Log.d(TAG, "Actively retrieving the tasks from the DataBase")
        tasksRepository = TasksRepository(database)
        tasks = tasksRepository.getloadAllTasks()
    }
}