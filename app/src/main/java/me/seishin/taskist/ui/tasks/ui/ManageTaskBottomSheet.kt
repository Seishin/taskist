package me.seishin.taskist.ui.tasks.ui

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import me.seishin.taskist.R
import me.seishin.taskist.data.entities.Task
import me.seishin.taskist.ui.tasks.TasksContract

class ManageTaskBottomSheet (private val presenter: TasksContract.TasksPresenter, private val taskForUpdate: Task? = null) : BottomSheetDialogFragment() {

    private var doneButton: Button? = null
    private var deleteButton: Button? = null
    private var titleField: EditText? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_manage_task_sheet, container, false)

        initUI(rootView)

        return rootView
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        (view?.parent as? View)?.setBackgroundColor(Color.TRANSPARENT)
    }

    private fun initUI(rootView: View?) {
        titleField = rootView?.findViewById(R.id.taskName) as EditText
        doneButton = rootView.findViewById<Button>(R.id.done) as Button
        deleteButton = rootView.findViewById<Button>(R.id.delete) as Button

        if (taskForUpdate != null) {
            configureUpdateTaskState(taskForUpdate)
        } else {
            configureCreateTaskState()
        }
    }

    private fun configureCreateTaskState() {
        doneButton?.setOnClickListener {
            presenter.createTask(Task(titleField?.text.toString(), false))
        }
    }

    private fun configureUpdateTaskState(taskForUpdate: Task) {
        titleField?.setText(taskForUpdate.title)
        doneButton?.text = "Update"
        deleteButton?.visibility = View.VISIBLE

        doneButton?.setOnClickListener {
            taskForUpdate.title = titleField?.text.toString()
            presenter.updateTask(taskForUpdate)
        }

        deleteButton?.setOnClickListener {
            presenter.deleteTask(taskForUpdate)
        }
    }
}