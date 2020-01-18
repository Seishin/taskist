package me.seishin.taskist.common.mvp

interface BaseContract {

    interface BasePresenter

    interface BaseView {
        fun onError(message: String)
    }
}