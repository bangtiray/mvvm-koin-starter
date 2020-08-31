package com.bangtiray.core

import android.app.Application
import android.content.Context
import com.bangtiray.core.di.auth
import com.bangtiray.core.di.dbModule
import com.bangtiray.core.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApp :Application(){
    companion object{
        lateinit var instance:CoreApp
        fun contextinism() = instance.applicationContext
    }

    override fun getApplicationContext(): Context {
        instance=this
        return super.getApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@CoreApp)
            modules(
                auth,
                networkModule,
                dbModule
            )
        }
    }
}