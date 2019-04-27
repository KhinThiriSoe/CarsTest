package com.sevenpeakssoftware.khinthirisoe.di.module

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.context.ApplicationContext
import dagger.Module
import dagger.Provides


@Module
class ApplicationModule(private val app: App) {

    @Provides
    fun app(): App = app

    @Provides
    @ApplicationContext
    fun provideApplicationContext(): Context {
        return app
    }

}
