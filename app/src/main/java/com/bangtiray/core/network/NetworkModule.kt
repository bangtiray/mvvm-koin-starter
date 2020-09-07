package com.bangtiray.core.network

import com.bangtiray.core.network.apps.AppsInstanceNetwork
import com.bangtiray.core.network.auth.AuthInstanceNetwork
import com.bangtiray.core.network.news.NewsInstanceNetwork
import org.koin.dsl.module

val networkModule= module {
    single { AuthInstanceNetwork.create() }
    single { NewsInstanceNetwork.create() }
    single { AppsInstanceNetwork.create() }
}