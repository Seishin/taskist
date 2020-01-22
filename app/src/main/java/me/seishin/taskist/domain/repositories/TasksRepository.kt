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
    fun createTask(task: Task): Maybe<Long>
    fun updateTask(task: Task): Maybe<Int>
    fun deleteTask(task: Task): Maybe<Int>
    fun filterTaskBy(filter: Filter): Flowable<List<Task>>
}