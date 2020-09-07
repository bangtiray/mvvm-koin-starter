package com.bangtiray.core.di

import android.app.Activity
import androidx.fragment.app.Fragment
import com.bangtiray.core.network.apps.AppsNetwork
import com.bangtiray.core.network.auth.AuthNetwork
import com.bangtiray.core.network.news.NewsNetwork

fun Activity.authNetwork(): Lazy<AuthNetwork> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {AuthNetwork(this)}
fun Fragment.authNetwork(): Lazy<AuthNetwork> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) { AuthNetwork(this)}
fun Fragment.newsNetwork(): Lazy<NewsNetwork> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {NewsNetwork(this)}
fun Fragment.appsNetwork(): Lazy<AppsNetwork> = lazy(LazyThreadSafetyMode.SYNCHRONIZED) {AppsNetwork(this)}