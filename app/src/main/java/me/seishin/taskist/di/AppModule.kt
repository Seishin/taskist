package me.seishin.taskist.di

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.domain.repositories.TasksRepository
import me.seishin.taskist.domain.repositories.TasksRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase(androidContext()).get() }
    single<TasksRepository> { TasksRepositoryImpl(get(), Schedulers.io(), AndroidSchedulers.mainThread()) }
}