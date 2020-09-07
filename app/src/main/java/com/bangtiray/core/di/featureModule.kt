package com.bangtiray.core.di

import com.bangtiray.core.data.repository.AppsRepository
import com.bangtiray.core.data.repository.NewsRepository
import com.bangtiray.core.ui.viewmodel.AppsViewModel
import com.bangtiray.core.ui.viewmodel.AuthViewModel
import com.bangtiray.core.ui.viewmodel.NewsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val auth = module { viewModel { AuthViewModel(get()) } }
val news = module {
    single { NewsRepository(get()) }
    viewModel { NewsViewModel(get()) }
}
val apps = module {
    single { AppsRepository(get()) }
    viewModel { AppsViewModel(get()) }
}