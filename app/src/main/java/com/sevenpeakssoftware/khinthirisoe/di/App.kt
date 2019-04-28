package com.sevenpeakssoftware.khinthirisoe.di

import android.app.Application
import com.sevenpeakssoftware.khinthirisoe.data.db.DbOpenHelper
import com.sevenpeakssoftware.khinthirisoe.data.db.model.DaoMaster
import com.sevenpeakssoftware.khinthirisoe.data.db.model.DaoSession
import com.sevenpeakssoftware.khinthirisoe.di.component.AppComponent
import com.sevenpeakssoftware.khinthirisoe.di.component.DaggerAppComponent
import com.sevenpeakssoftware.khinthirisoe.di.module.ApplicationModule

class App : Application() {

    companion object {
        @JvmStatic
        lateinit var appComponent: AppComponent

        var mDaoSession: DaoSession? = null
    }

    override fun onCreate() {
        super.onCreate()

        appComponent =
            DaggerAppComponent.builder().applicationModule(ApplicationModule(this)).build()

        appComponent.inject(this)

        mDaoSession = DaoMaster(DbOpenHelper(this, "car_article").writableDb).newSession()

    }
}

