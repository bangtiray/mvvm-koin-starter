package com.bangtiray.core.network

import org.koin.dsl.module

val networkModule= module {
    single { AuthInstanceNetwork.create() }
}