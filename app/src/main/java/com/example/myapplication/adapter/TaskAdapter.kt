package com.example.myapplication.adapter

import android.content.Context
import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.database.TaskContract
import com.example.myapplication.database.TaskEntry
import java.text.SimpleDateFormat
import java.util.*

class TaskAdapter
(
    private val mContext: Context,
    private val mItemClickListener: ItemClickListener
) : RecyclerView.Adapter<TaskAdapter.TaskViewHolder>() {
    private var mTaskEntries: List<TaskEntry>? = null
    private val dateFormat = SimpleDateFormat(DATE_FORMAT, Locale.getDefault())

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view = LayoutInflater.from(mContext)
            .inflate(R.layout.task_layout, parent, false)
        return TaskViewHolder(view)
    }
    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
        val taskEntry = mTaskEntries!![position]
        val description = taskEntry.description
        val priority = taskEntry.priority
        val updatedAt = dateFormat.format(taskEntry.updatedAt)

        //Set values
        holder.taskDescriptionView.text = description
        holder.updatedAtView.text = updatedAt

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
        return if (mTaskEntries == null) {
            0
        } else mTaskEntries!!.size
    }

    var tasks: List<TaskEntry>?
        get() = mTaskEntries
        set(taskEntries) {
            mTaskEntries = taskEntries
            notifyDataSetChanged()
        }

    interface ItemClickListener {
        fun onItemClickListener(itemId: Int)
    }

    // Inner class for creating ViewHolders
    inner class TaskViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView),
        View.OnClickListener {
        var taskDescriptionView: TextView = itemView.findViewById(R.id.taskDescription)
        var updatedAtView: TextView = itemView.findViewById(R.id.taskUpdatedAt)
        var priorityView: TextView = itemView.findViewById(R.id.priorityTextView)
        override fun onClick(view: View) {
            val elementId = mTaskEntries!![adapterPosition].id
            mItemClickListener.onItemClickListener(elementId)
        }
        init {
            itemView.setOnClickListener(this)
        }
    }

    companion object {
        // Constant for date format
        private const val DATE_FORMAT = "dd/MM/yyy"
    }
}