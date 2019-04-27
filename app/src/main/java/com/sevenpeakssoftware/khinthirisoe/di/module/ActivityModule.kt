package com.sevenpeakssoftware.khinthirisoe.di.module

import android.app.Activity
import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.di.context.ActivityContext
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

}