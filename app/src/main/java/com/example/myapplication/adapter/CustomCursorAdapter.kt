package com.example.myapplication.adapter

import android.content.Context
import android.database.Cursor
import android.graphics.drawable.GradientDrawable
import android.provider.BaseColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.database.TaskContract
import com.example.myapplication.R

class CustomCursorAdapter
    (private val mContext: Context) : RecyclerView.Adapter<CustomCursorAdapter.TaskViewHolder>() {
    private var mCursor: Cursor? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {

        // Inflate the task_layout to a view
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        // Indices for the _id, description, and priority columns
        val idIndex = mCursor!!.getColumnIndex(BaseColumns._ID)
        val descriptionIndex = mCursor!!.getColumnIndex(TaskContract.TaskEntry.COLUMN_DESCRIPTION)
        val priorityIndex = mCursor!!.getColumnIndex(TaskContract.TaskEntry.COLUMN_PRIORITY)
        mCursor!!.moveToPosition(position)

        // Determine the values of the wanted data
        val id = mCursor!!.getInt(idIndex)
        val description = mCursor!!.getString(descriptionIndex)
        val priority = mCursor!!.getInt(priorityIndex)

        //Set values
        holder.itemView.tag = id
        holder.taskDescriptionView.text = description

        // Programmatically set the text and color for the priority TextView
        val priorityString = "" + priority // converts int to String
        holder.priorityView.text = priorityString
        val priorityCircle = holder.priorityView.background as GradientDrawable
        // Get the appropriate background color based on the priority
        val priorityColor = getPriorityColor(priority)
        priorityCircle.setColor(priorityColor)
    }

    private fun getPriorityColor(priority: Int): Int {
        var priorityColor = 0
        when (priority) {
            1 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialRed)
            2 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialYellow)
            3 -> priorityColor = ContextCompat.getColor(mContext, R.color.materialGreen)
            else -> {}
        }
        return priorityColor
    }

    override fun getItemCount(): Int {
        return if (mCursor == null) {
            0
        } else mCursor!!.count
    }
    fun swapCursor(c: Cursor?): Cursor? {
        // check if this cursor is the same as the previous cursor (mCursor)
        if (mCursor === c) {
            return null // bc nothing has changed
        }
        val temp = mCursor
        mCursor = c // new cursor value assigned

        //check if this is a valid cursor, then update the cursor
        if (c != null) {
            notifyDataSetChanged()
        }
        return temp
    }

    // Inner class for creating ViewHolders
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        // Class variables for the task description and priority TextViews
        var taskDescriptionView: TextView = itemView.findViewById<View>(R.id.taskDescription) as TextView
        var priorityView: TextView = itemView.findViewById<View>(R.id.priorityTextView) as TextView

    }
}