package me.seishin.taskist.ui.tasks.ui.adapters

import me.seishin.taskist.data.entities.Task

interface TaskActionsListener {
    fun updateTaskRequest(task: Task)
    fun deleteTaskRequest(task: Task)
    fun onTaskTapAction(task: Task)
}