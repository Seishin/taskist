package me.seishin.taskist.di

import me.seishin.taskist.data.AppDatabase
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module

val appModule = module {
    single { AppDatabase(androidContext()).get() }
}