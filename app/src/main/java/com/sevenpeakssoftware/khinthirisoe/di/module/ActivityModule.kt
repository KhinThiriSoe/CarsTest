package com.sevenpeakssoftware.khinthirisoe.di.module

import android.app.Activity
import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.data.network.ApiService
import com.sevenpeakssoftware.khinthirisoe.di.context.ActivityContext
import com.sevenpeakssoftware.khinthirisoe.ui.article.ArticleContract
import com.sevenpeakssoftware.khinthirisoe.ui.article.model.ArticleRepository
import com.sevenpeakssoftware.khinthirisoe.ui.article.presenter.ArticlePresenter
import dagger.Module
import dagger.Provides

@Module
class ActivityModule(private val activity: Activity) {

    @Provides
    fun activityContext(): Context = activity

    @Provides
    @ActivityContext
    fun provideActivityContext(): Context {
        return activity
    }

    @Provides
    fun articleRepository(apiService: ApiService): ArticleRepository = ArticleRepository(apiService)

    @Provides
    fun articlePresenter(repository: ArticleRepository): ArticleContract.Presenter = ArticlePresenter(repository)


}