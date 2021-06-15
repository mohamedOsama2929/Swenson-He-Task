package com.test.swensonhetask.appmanger

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

class CurrenciesApp : Application() {


    init {
        instance = this
    }

    companion object {
        private var instance: CurrenciesApp? = null
        fun applicationContext(): Context {
            return instance!!.applicationContext
        }

    }

    override fun attachBaseContext(base: Context) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

}