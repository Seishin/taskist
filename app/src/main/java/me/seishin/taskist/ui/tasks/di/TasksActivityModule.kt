package me.seishin.taskist.ui.tasks.di

import me.seishin.taskist.ui.tasks.ui.ManageTaskBottomSheet
import me.seishin.taskist.ui.tasks.TasksContract
import me.seishin.taskist.ui.tasks.ui.TasksListActivity
import me.seishin.taskist.ui.tasks.TasksPresenterImpl
import me.seishin.taskist.ui.tasks.ui.adapters.TaskActionsListener
import me.seishin.taskist.ui.tasks.ui.adapters.TasksListAdapter
import org.koin.core.qualifier.named
import org.koin.dsl.module

var tasksActivityModule = module {
    scope(named<TasksListActivity>()) {
        scoped<TasksContract.TasksPresenter> { (view: TasksContract.TasksView) -> TasksPresenterImpl(view, get()) }
        scoped { (listener: TaskActionsListener) -> TasksListAdapter(listener) }
    }
}