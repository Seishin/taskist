package me.seishin.taskist

import android.app.Application
import me.seishin.taskist.di.appModule
import me.seishin.taskist.ui.tasks.di.tasksActivityModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class TaskistApp: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@TaskistApp)

            modules(arrayListOf(appModule, tasksActivityModule))
        }
    }
}