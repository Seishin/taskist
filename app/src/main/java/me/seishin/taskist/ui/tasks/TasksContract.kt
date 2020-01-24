package me.seishin.taskist.ui.tasks

import me.seishin.taskist.common.mvp.BaseContract
import me.seishin.taskist.data.entities.Task
import java.util.logging.Filter

interface TasksContract {

    interface TasksPresenter: BaseContract.BasePresenter {
        fun createTask(task: Task)
        fun updateTask(task: Task)
        fun deleteTask(task: Task)
        fun getAllTasks()
        fun onStop()
    }

    interface TasksView: BaseContract.BaseView {
        fun onTasksListObtained(tasks: List<Task>)
        fun onTaskCreated()
        fun onTaskUpdated(task: Task)
        fun onTaskDeleted(task: Task)
    }
}