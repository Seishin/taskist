package me.seishin.taskist.domain.repositories

import io.reactivex.*
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.common.Filter
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

    override fun createTask(task: Task): Completable {
        return db.tasksDao().createTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun updateTask(task: Task): Completable {
        return db.tasksDao().updateTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun deleteTask(task: Task): Completable {
        return db.tasksDao().deleteTask(task).subscribeOn(subscribeScheduler).observeOn(observeScheduler)
    }

    override fun filterTaskBy(filter: Filter): Flowable<List<Task>> {
        return Flowable.empty()
    }
}