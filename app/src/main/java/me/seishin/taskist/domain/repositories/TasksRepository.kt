package me.seishin.taskist.domain.repositories

import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.seishin.taskist.common.Filter
import me.seishin.taskist.data.entities.Task

interface TasksRepository {
    fun getAllTasks(): Flowable<List<Task>>
    fun getTask(id: Int): Single<Task>
    fun createTask(task: Task): Completable
    fun updateTask(task: Task): Completable
    fun deleteTask(task: Task): Completable
    fun filterTaskBy(filter: Filter): Flowable<List<Task>>
}