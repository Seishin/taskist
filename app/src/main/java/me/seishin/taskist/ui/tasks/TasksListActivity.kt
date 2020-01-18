package me.seishin.taskist.ui.tasks

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import me.seishin.taskist.R
import me.seishin.taskist.data.entities.Task
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class TasksListActivity : AppCompatActivity(), TasksContract.TasksView {

    private val presenter: TasksContract.TasksPresenter by currentScope.inject { parametersOf(this@TasksListActivity)}

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        presenter.getAllTasks()
    }

    override fun onTasksListObtained(tasks: List<Task>) {
    }

    override fun onTaskObtained(task: Task) {
    }

    override fun onTaskCreated() {
    }

    override fun onTaskUpdated(task: Task) {
    }

    override fun onTaskDeleted(task: Task) {
    }

    override fun onError(message: String) {
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }
}
