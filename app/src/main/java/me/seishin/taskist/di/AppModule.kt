package me.seishin.taskist.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.domain.repositories.TasksRepository
import me.seishin.taskist.domain.repositories.TasksRepositoryImpl
import me.seishin.taskist.ui.tasks.TasksContract
import me.seishin.taskist.ui.tasks.TasksListActivity
import me.seishin.taskist.ui.tasks.TasksPresenterImpl
import org.koin.android.ext.koin.androidContext
import org.koin.core.qualifier.named
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase(androidContext()).get() }
    single<TasksRepository> { TasksRepositoryImpl(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }

    scope(named<TasksListActivity>()) {
        scoped<TasksContract.TasksPresenter> { (view: TasksContract.TasksView) -> TasksPresenterImpl(view, get()) }
    }
}