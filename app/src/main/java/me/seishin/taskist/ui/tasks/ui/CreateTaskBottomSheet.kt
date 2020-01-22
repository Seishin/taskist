package me.seishin.taskist.ui.tasks.ui

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

class CreateTaskBottomSheet (private val presenter: TasksContract.TasksPresenter) : BottomSheetDialogFragment() {

    private lateinit var titleField: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val rootView = inflater.inflate(R.layout.fragment_create_task_sheet, container, false)

        initUI(rootView)

        return rootView
    }

    private fun initUI(rootView: View?) {
        titleField = rootView?.findViewById(R.id.taskName) as EditText

        rootView.findViewById<Button>(R.id.done).setOnClickListener {
            presenter.createTask(Task(titleField.text.toString(), false))
        }
    }
}