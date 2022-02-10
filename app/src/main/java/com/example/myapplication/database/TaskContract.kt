package com.example.myapplication.database

import android.net.Uri
import android.provider.BaseColumns

object TaskContract {
    const val AUTHORITY = "com.example.android.todolist"
    val BASE_CONTENT_URI = Uri.parse("content://$AUTHORITY")
    const val PATH_TASKS = "tasks"

    object TaskEntry : BaseColumns {
        val CONTENT_URI = BASE_CONTENT_URI.buildUpon().appendPath(PATH_TASKS).build()

        // Task table and column names
        const val TABLE_NAME = "tasks"

        const val COLUMN_DESCRIPTION = "description"
        const val COLUMN_PRIORITY = "priority"
    }
}