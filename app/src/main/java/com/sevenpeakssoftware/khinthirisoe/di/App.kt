package com.sevenpeakssoftware.khinthirisoe.di

import android.app.Application
import com.sevenpeakssoftware.khinthirisoe.di.component.AppComponent
import com.sevenpeakssoftware.khinthirisoe.di.component.DaggerAppComponent
import com.sevenpeakssoftware.khinthirisoe.di.module.ApplicationModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()

        appComponent.inject(this)
    }
}

