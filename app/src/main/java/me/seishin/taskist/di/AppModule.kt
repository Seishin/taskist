package me.seishin.taskist.di

import me.seishin.taskist.data.AppDatabase
import me.seishin.taskist.data.domain.repositories.TasksRepository
import me.seishin.taskist.data.domain.repositories.TasksRepositoryImpl
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase(androidContext()).get() }
    single<TasksRepository> { TasksRepositoryImpl(get()) }
}