package com.bangtiray.core

import android.app.Application
import android.content.Context
import com.bangtiray.core.data.repository.PreferenceRepository
import com.bangtiray.core.di.apps
import com.bangtiray.core.di.auth
import com.bangtiray.core.di.dbModule
import com.bangtiray.core.di.news
import com.bangtiray.core.network.networkModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class CoreApp : Application() {
    lateinit var preferenceRepository: PreferenceRepository

    companion object {
        const val DEFAULT_PREFERENCES = "default_preferences"
        lateinit var instance: CoreApp
        fun contextinism() = instance.applicationContext

    }

    override fun getApplicationContext(): Context {
        instance = this
        return super.getApplicationContext()
    }

    override fun onCreate() {
        super.onCreate()

        preferenceRepository =
            PreferenceRepository(getSharedPreferences(DEFAULT_PREFERENCES, Context.MODE_PRIVATE))
        startKoin {
            androidContext(this@CoreApp)
            modules(
                auth,
                news,
                apps,
                networkModule,
                dbModule
            )
        }
    }
}