package me.seishin.taskist

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.domain.repositories.TasksRepository
import me.seishin.taskist.data.entities.Task
import org.koin.android.ext.android.inject

class MainActivity : AppCompatActivity() {

    private val repository: TasksRepository by inject()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val s = repository.getAllTasks().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe { println(it) }
    }
}
