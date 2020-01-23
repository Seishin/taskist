package me.seishin.taskist.data.daos

import androidx.room.*
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single
import me.seishin.taskist.data.entities.Task

@Dao
interface TasksDao {

    @Query("SELECT * FROM table_tasks WHERE id == :id")
    fun getTask(id: Int): Single<Task>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun createTask(task: Task): Maybe<Long>

    @Update(onConflict = OnConflictStrategy.REPLACE)
    fun updateTask(task: Task): Maybe<Int>

    @Delete
    fun deleteTask(task: Task): Maybe<Int>

    @Query("SELECT * FROM table_tasks ORDER BY isChecked ASC, createdAt DESC")
    fun getAllTasks(): Flowable<List<Task>>
}