package com.bangtiray.core.ui.activity.auth.ui.login

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.bangtiray.core.database.LocalUserDatabase
import com.bangtiray.core.database.entity.LocalUser
import com.bangtiray.core.network.auth.AuthNetwork
import com.bangtiray.core.utils.logi
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class LoginViewModel : ViewModel() {

    enum class AuthenticationState {
        UNAUTHENTICATED,        // Initial state, the user needs to authenticate
        AUTHENTICATED,        // The user has authenticated successfully
        INVALID_AUTHENTICATION  // Authentication failed
    }

    val authenticationState = MutableLiveData<AuthenticationState>()
    var username: String

    init {
        // In this example, the user is always unauthenticated when MainActivity is launched
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
        username = ""
    }

    fun refuseAuthentication() {
        authenticationState.value = AuthenticationState.UNAUTHENTICATED
    }

    fun authenticate(
        username: String,
        password: String,
        localUserDb: LocalUserDatabase,
        authNetwork: AuthNetwork,
        loginFragment: LoginFragment
    ) {
        val devToken="dZrAs8z5T-GNn8WXHW4sC2:APA91bHdWwasOKyFUhvcCpMzaxQ0GDSftVY4xqLqnxIob83xqF3rUTdu9nXr_BYWJIPosIcK-7f2d3T4B-dBQPICPPYwBdOOZc0J7bmRssT-mDJyEhMK5tfkVDp0zBxIN0omR1kJ1x0M"
        authNetwork.login(loginFragment.requireActivity(),username,password, devToken, object : AuthNetwork.AuthNetworkCallback {
            override fun onSuccess(localUser: LocalUser) {
                logi(localUser.first_name)

                GlobalScope.launch {
                    localUserDb.localUserDao().insert(localUser)
                }
                authenticationState.value = AuthenticationState.AUTHENTICATED
            }

            override fun onFailed(message: String?) {
                logi(message)
                authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
            }

        })
//        if (passwordIsValidForUsername(username, password)) {
//            this.username = username
//            val devToken="dZrAs8z5T-GNn8WXHW4sC2:APA91bHdWwasOKyFUhvcCpMzaxQ0GDSftVY4xqLqnxIob83xqF3rUTdu9nXr_BYWJIPosIcK-7f2d3T4B-dBQPICPPYwBdOOZc0J7bmRssT-mDJyEhMK5tfkVDp0zBxIN0omR1kJ1x0M"
//            authenticationState.value = AuthenticationState.AUTHENTICATED
//
//        } else {
//            authenticationState.value = AuthenticationState.INVALID_AUTHENTICATION
//        }
    }

    private fun passwordIsValidForUsername(username: String, password: String): Boolean {
        return true
    }

}