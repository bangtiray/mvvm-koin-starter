package com.bangtiray.core.di

import com.bangtiray.core.ui.viewmodel.AuthViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val auth = module {
    viewModel { AuthViewModel(get()) }
}