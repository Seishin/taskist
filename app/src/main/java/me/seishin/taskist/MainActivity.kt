package me.seishin.taskist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.entities.Task
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val database: AppDatabase.Database by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val subscription = database.tasksDao().getAllTasks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe {
            println(it)
        }

        val task = Task(title = "Hello World", isChecked = false, createdAt = System.currentTimeMillis(), updatedAt = System.currentTimeMillis())
        val createSubscription = database.tasksDao().createTask(task)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe {
                println("Added...")
            }
    }
}
