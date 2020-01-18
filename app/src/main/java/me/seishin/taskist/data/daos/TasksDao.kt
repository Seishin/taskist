package me.seishin.taskist.data.daos

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import io.reactivex.Completable
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.seishin.taskist.data.entities.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM table_tasks WHERE id == :id")
    fun getTask(id: Int): Single<Task>

    @Insert
    fun createTask(task: Task): Completable

    @Insert
    fun updateTask(task: Task): Completable

    @Delete
    fun deleteTask(task: Task): Completable

    @Query("SELECT * FROM table_tasks")
    fun getAllTasks(): Flowable<List<Task>>
}