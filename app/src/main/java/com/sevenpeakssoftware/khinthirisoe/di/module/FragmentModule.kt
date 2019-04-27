package com.sevenpeakssoftware.khinthirisoe.di.module

import android.content.Context
import androidx.fragment.app.Fragment
import com.sevenpeakssoftware.khinthirisoe.di.context.ActivityContext
import dagger.Module
import dagger.Provides


@Module
class FragmentModule(private val fragment: Fragment) {

    @Provides
    fun provideFragment(): Fragment {
        return fragment
    }

    @Provides
    @ActivityContext
    fun provideFragmentContext(): Context {
        return fragment.context!!
    }
}
