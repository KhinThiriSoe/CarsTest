package com.sevenpeakssoftware.khinthirisoe.di.component

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.di.context.ApplicationContext
import com.sevenpeakssoftware.khinthirisoe.di.module.ActivityModule
import com.sevenpeakssoftware.khinthirisoe.di.scope.ActivityScope
import dagger.Component

@ActivityScope
@Component(dependencies = [(AppComponent::class)], modules = [ActivityModule::class])
interface ActivityComponent : AppComponent {

    @ApplicationContext
    override fun getContext(): Context

}