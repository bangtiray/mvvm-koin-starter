package com.bangtiray.core.data.repository

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import com.bangtiray.core.utils.logi

class AuthRepository (context: Context){
    fun login(activity: AppCompatActivity, userName:String, password:String, deviceToken:String){
       logi(userName)
    }
}