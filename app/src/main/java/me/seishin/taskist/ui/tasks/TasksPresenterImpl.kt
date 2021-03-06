package me.seishin.taskist.ui.tasks

import io.reactivex.disposables.CompositeDisposable
import me.seishin.taskist.data.entities.Task
import me.seishin.taskist.domain.repositories.TasksRepository
import java.util.logging.Filter

class TasksPresenterImpl(private val view: TasksContract.TasksView, private val repository: TasksRepository): TasksContract.TasksPresenter {

    private val compositeDisposable = CompositeDisposable()

    override fun getAllTasks() {
        val subscription = repository.getAllTasks().subscribe({
            view.onTasksListObtained(it)
        }, {
            it.message?.let { msg -> view.onError(msg) }
        })
        compositeDisposable.add(subscription)
    }

    override fun createTask(task: Task) {
        val subscription = repository.createTask(task).subscribe({ id ->
            task.id = id
            view.onTaskCreated()
        }, {
            it.message?.let { msg -> view.onError(msg)}
        })
        compositeDisposable.add(subscription)
    }

    override fun updateTask(task: Task) {
        val subscription = repository.updateTask(task).subscribe({ id ->
            view.onTaskUpdated(task)
        }, {
            it.message?.let { msg -> view.onError(msg) }
        })
        compositeDisposable.add(subscription)
    }

    override fun deleteTask(task: Task) {
        val subscription = repository.deleteTask(task).subscribe({
            view.onTaskDeleted(task)
        }, {
            it.message?.let { msg -> view.onError(msg) }
        })
        compositeDisposable.add(subscription)
    }

    override fun onStop() {
        compositeDisposable.clear()
    }
}