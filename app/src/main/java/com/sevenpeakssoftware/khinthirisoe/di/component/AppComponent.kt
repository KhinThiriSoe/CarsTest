package com.sevenpeakssoftware.khinthirisoe.di.component

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.data.network.ApiService
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.context.ApplicationContext
import com.sevenpeakssoftware.khinthirisoe.di.module.ApplicationModule
import dagger.Component
import javax.inject.Singleton

@Singleton
@Component(modules = [(ApplicationModule::class)])
interface AppComponent {

    @ApplicationContext
    fun getContext(): Context

    fun inject(application: App)

    fun app(): App

    fun apiService(): ApiService

}
