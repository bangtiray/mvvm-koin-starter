package com.bangtiray.core.ui.viewmodel

import android.app.Application
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.AndroidViewModel
import com.bangtiray.core.data.repository.AuthRepository

class AuthViewModel(application: Application) : AndroidViewModel(application) {

    private val repository = AuthRepository(application.applicationContext)
    fun login(activity:AppCompatActivity, userName:String, password:String, deviceToken:String) =repository.login(activity, userName, password, deviceToken)

}