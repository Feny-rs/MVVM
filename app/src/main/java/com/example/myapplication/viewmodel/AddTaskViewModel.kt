package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.database.AppDatabase
import com.example.myapplication.database.TaskEntry
import com.example.myapplication.database.TasksRepository

class AddTaskViewModel(database: AppDatabase?, taskId: Int) : ViewModel() {
    val task: LiveData<TaskEntry?>?
    private val tasksRepository: TasksRepository = TasksRepository(database!!)
    fun updateTask(task: TaskEntry?) {
        tasksRepository.updateTaskById(task)
    }
    init {
        task = tasksRepository.getloadTaskById(taskId)
    }
}