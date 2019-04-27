package com.sevenpeakssoftware.khinthirisoe.di.component

import android.content.Context
import com.sevenpeakssoftware.khinthirisoe.di.context.ActivityContext
import com.sevenpeakssoftware.khinthirisoe.di.module.FragmentModule
import com.sevenpeakssoftware.khinthirisoe.di.scope.FragmentScope
import dagger.Component

@FragmentScope
@Component(dependencies = [(AppComponent::class)], modules = [(FragmentModule::class)])
interface FragmentComponent {

    @get:ActivityContext
    var context: Context
}