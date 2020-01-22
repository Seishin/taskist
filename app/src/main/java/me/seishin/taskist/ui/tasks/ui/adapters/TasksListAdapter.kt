package me.seishin.taskist.ui.tasks.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.layout_task_view.view.*
import me.seishin.taskist.R
import me.seishin.taskist.data.entities.Task

class TasksListAdapter (private val listener: OnTaskChangeListener): RecyclerView.Adapter<TasksListAdapter.ViewHolder>() {

    private val data = arrayListOf<Task>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.layout_task_view, parent, false))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val task = data[position]

        holder.itemView.checkbox.text = task.title
        holder.itemView.checkbox.setOnCheckedChangeListener { _, isChecked ->
            if (task.isChecked == isChecked) return@setOnCheckedChangeListener

            task.updateCheckedState(isChecked)
            listener.updateTaskRequest(task)
        }
        holder.itemView.checkbox.isChecked = task.isChecked
    }

    override fun getItemCount() = data.size

    fun insertData(tasks: List<Task>) {
        this.data.clear()
        this.data.addAll(tasks)
        notifyDataSetChanged()
    }

    class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView)
}