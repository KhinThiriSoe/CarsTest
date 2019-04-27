package com.sevenpeakssoftware.khinthirisoe.ui.base

interface BasePresenter<V : BaseView> {

    fun onAttachView(view: V)

    fun onDetachView()

}