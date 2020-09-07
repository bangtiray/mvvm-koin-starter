package com.bangtiray.core.di

import com.bangtiray.core.database.LocalAppsDatabase
import com.bangtiray.core.database.LocalNewsDatabase
import com.bangtiray.core.database.LocalUserDatabase
import org.koin.dsl.module

val dbModule = module {
    single { LocalUserDatabase.getInstance(get()) }
    single { LocalNewsDatabase.getInstance(get()) }
    single { LocalAppsDatabase.getInstance(get()) }
}