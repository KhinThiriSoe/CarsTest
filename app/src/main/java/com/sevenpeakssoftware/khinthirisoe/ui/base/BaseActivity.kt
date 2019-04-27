package com.sevenpeakssoftware.khinthirisoe.ui.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.sevenpeakssoftware.khinthirisoe.di.App
import com.sevenpeakssoftware.khinthirisoe.di.component.AppComponent

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupComponent(App.appComponent)
    }

    protected abstract fun setupComponent(appComponent: AppComponent)

}
