package com.bangtiray.core.network.auth

import android.app.Activity
import android.content.ComponentCallbacks
import com.bangtiray.core.database.entity.LocalUser
import com.bangtiray.core.utils.logi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.koin.android.ext.android.inject

class AuthNetwork(componentCallbacks: ComponentCallbacks) {
    private val authInstanceNetwork: AuthInstanceNetwork by componentCallbacks.inject()
    fun login(
        activity: Activity,
        userName: String,
        password: String,
        deviceToken: String,
        authNetworkCallback: AuthNetworkCallback
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            try {
                logi("login....")
                val response = authInstanceNetwork.postLogin(userName, password, deviceToken)
                if (response.message == "Berhasil Masuk.") {
                    activity.runOnUiThread{
                        logi("Login Success")
                        authNetworkCallback.onSuccess(response)
                    }
                }else{
                    activity.runOnUiThread{
                        logi("Login Failed")
                    }
                }
            }catch (e:Throwable){
                activity.runOnUiThread{
                    logi("Login Failed")
                    authNetworkCallback.onFailed(e.localizedMessage)
                }
            }
        }

    }

    interface AuthNetworkCallback {
        fun onSuccess(localUser: LocalUser)
        fun onFailed(message: String?)
    }
}