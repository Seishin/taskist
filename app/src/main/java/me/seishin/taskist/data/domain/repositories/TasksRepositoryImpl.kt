package me.seishin.taskist.data.domain.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.common.Filter
import me.seishin.taskist.data.entities.Task

class TasksRepositoryImpl (private val db: AppDatabase.Database): TasksRepository {

    override fun getAllTasks(): Flowable<List<Task>> {
        return db.tasksDao().getAllTasks()
    }

    override fun getTask(id: Int): Single<Task> {
        return db.tasksDao().getTask(id)
    }

    override fun createTask(task: Task): Completable {
        return db.tasksDao().createTask(task)
    }

    override fun updateTask(task: Task): Completable {
        return db.tasksDao().updateTask(task)
    }

    override fun deleteTask(task: Task): Maybe<Int> {
        return db.tasksDao().deleteTask(task)
    }

    override fun filterTaskBy(filter: Filter): Flowable<List<Task>> {
        return Flowable.empty()
    }
}