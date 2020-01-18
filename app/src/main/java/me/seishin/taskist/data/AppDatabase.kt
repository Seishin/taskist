package me.seishin.taskist.data

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import me.seishin.taskist.data.daos.TasksDao
import me.seishin.taskist.data.entities.Task

class AppDatabase (private val context: Context) {

    @androidx.room.Database(entities = [Task::class], version = 1)
    abstract class Database: RoomDatabase() {
        abstract fun tasksDao(): TasksDao
    }

    private val appDatabase = Room.databaseBuilder(context.applicationContext, Database::class.java, "tasks.db").build()

    fun get(): Database {
        return appDatabase
    }
}

