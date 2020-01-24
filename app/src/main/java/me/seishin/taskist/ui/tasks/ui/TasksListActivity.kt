package me.seishin.taskist.ui.tasks.ui

import android.os.Bundle
import android.view.View
import android.view.animation.LinearInterpolator
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import me.seishin.taskist.R
import me.seishin.taskist.data.entities.Task
import me.seishin.taskist.ui.tasks.TasksContract
import me.seishin.taskist.ui.tasks.ui.adapters.TaskActionsListener
import me.seishin.taskist.ui.tasks.ui.adapters.TasksListAdapter
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class TasksListActivity : AppCompatActivity(), TasksContract.TasksView, TaskActionsListener {

    private val presenter: TasksContract.TasksPresenter by currentScope.inject { parametersOf(this@TasksListActivity)}
    private val tasksAdapter: TasksListAdapter by currentScope.inject { parametersOf(this) }

    private var manageTaskBottomSheet: ManageTaskBottomSheet? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        tasksList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tasksList.adapter = tasksAdapter

        add.setOnClickListener {
            manageTaskBottomSheet = ManageTaskBottomSheet(presenter)
            manageTaskBottomSheet?.let { it.show(supportFragmentManager, it.tag) }
        }
    }

    override fun onResume() {
        super.onResume()
        presenter.getAllTasks()
    }

    override fun onStop() {
        presenter.onStop()
        super.onStop()
    }

    override fun onTasksListObtained(tasks: List<Task>) {
        tasksAdapter.insertData(tasks)

        if (tasks.isNotEmpty()) {
            emptyStateContainer.animate().alpha(0f).setStartDelay(200).setDuration(180).withEndAction {
                tasksList.animate().setDuration(180).alpha(1f).setInterpolator(LinearInterpolator()).start()
            }.setInterpolator(LinearInterpolator()).start()
        } else {
            emptyStateContainer.animate().alpha(1f).setStartDelay(200).withStartAction {
                tasksList.animate().setDuration(180).alpha(0f).setInterpolator(LinearInterpolator()).start()
            }.setDuration(180).setInterpolator(LinearInterpolator()).start()
        }
    }

    override fun onTaskCreated() {
        manageTaskBottomSheet?.dismiss()
    }

    override fun onTaskUpdated(task: Task) {
        manageTaskBottomSheet?.dismiss()
    }

    override fun onTaskDeleted(task: Task) {
        manageTaskBottomSheet?.dismiss()
        val snackbar = Snackbar.make(container, getString(R.string.message_task_deleted), Snackbar.LENGTH_LONG)
        snackbar.setAction(getString(R.string.action_undo)) {
            presenter.createTask(task)
        }
        snackbar.show()
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun updateTaskRequest(task: Task) {
        presenter.updateTask(task)
    }

    override fun deleteTaskRequest(task: Task) {

    }

    override fun onTaskTapAction(task: Task) {
        manageTaskBottomSheet = ManageTaskBottomSheet(presenter, task)
        manageTaskBottomSheet?.let { it.show(supportFragmentManager, it.tag) }
    }
}
