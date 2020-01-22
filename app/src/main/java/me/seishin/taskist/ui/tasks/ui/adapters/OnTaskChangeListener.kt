package me.seishin.taskist.ui.tasks.ui.adapters

import me.seishin.taskist.data.entities.Task

interface OnTaskChangeListener {
    fun updateTaskRequest(task: Task)
    fun deleteTaskRequest(task: Task)
}