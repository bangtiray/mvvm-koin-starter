package com.bangtiray.core.di

import android.app.Activity
import com.bangtiray.core.network.AuthNetwork

fun Activity.authNetwork(): Lazy<AuthNetwork> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { AuthNetwork(this) }