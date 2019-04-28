package com.sevenpeakssoftware.khinthirisoe.di.component

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.di.context.ApplicationContext
import com.sevenpeakssoftware.khinthirisoe.di.module.ActivityModule
import com.sevenpeakssoftware.khinthirisoe.di.scope.ActivityScope
import com.sevenpeakssoftware.khinthirisoe.ui.article.ArticleContract
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.ArticleRepository
import com.sevenpeakssoftware.khinthirisoe.ui.article.view.ArticleActivity
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

    fun articleRepository(): ArticleRepository

    fun articlePresenter(): ArticleContract.Presenter

    fun inject(activity: ArticleActivity)

}