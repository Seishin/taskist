package me.seishin.taskist.domain.repositories

import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Scheduler
import io.reactivex.Single
import me.seishin.taskist.common.Filter
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.entities.Task

class TasksRepositoryImpl (
    private val db: AppDatabase.Database,
    private val subscribeScheduler: Scheduler,
    private val observeScheduler: Scheduler): TasksRepository {

    override fun getAllTasks(): Flowable<List<Task>> {
        return db.tasksDao().getAllTasks().subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun getTask(id: Int): Single<Task> {
        return db.tasksDao().getTask(id).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun createTask(task: Task): Maybe<Long> {
        return db.tasksDao().createTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun updateTask(task: Task): Maybe<Int> {
        return db.tasksDao().updateTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun deleteTask(task: Task): Maybe<Int> {
        return db.tasksDao().deleteTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun filterTaskBy(filter: Filter): Flowable<List<Task>> {
        return Flowable.empty()
    }
}