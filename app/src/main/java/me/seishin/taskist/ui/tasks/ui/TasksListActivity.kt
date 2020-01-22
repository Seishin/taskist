package me.seishin.taskist.ui.tasks.ui

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import me.seishin.taskist.R
import me.seishin.taskist.data.entities.Task
import me.seishin.taskist.ui.tasks.TasksContract
import me.seishin.taskist.ui.tasks.ui.adapters.OnTaskChangeListener
import me.seishin.taskist.ui.tasks.ui.adapters.TasksListAdapter
import org.koin.androidx.scope.currentScope
import org.koin.core.parameter.parametersOf

class TasksListActivity : AppCompatActivity(), TasksContract.TasksView, OnTaskChangeListener {

    private val presenter: TasksContract.TasksPresenter by currentScope.inject { parametersOf(this@TasksListActivity)}
    private val tasksAdapter: TasksListAdapter by currentScope.inject { parametersOf(this) }
    private val createTaskBottomSheet: CreateTaskBottomSheet by currentScope.inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()
    }

    private fun initUI() {
        tasksList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        tasksList.adapter = tasksAdapter

        add.setOnClickListener {
            createTaskBottomSheet.show(supportFragmentManager, createTaskBottomSheet.tag)
        }

        initCreateTaskBottomSheet()
    }

    private fun initCreateTaskBottomSheet() {

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
    }

    override fun onTaskObtained(task: Task) {

    }

    override fun onTaskCreated() {
        createTaskBottomSheet.dismiss()
    }

    override fun onTaskUpdated(task: Task) {
    }

    override fun onTaskDeleted(task: Task) {
    }

    override fun onError(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_LONG).show()
    }

    override fun updateTaskRequest(task: Task) {
        presenter.updateTask(task)
    }

    override fun deleteTaskRequest(task: Task) {

    }
}
