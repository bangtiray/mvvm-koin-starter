package com.bangtiray.core.ui.activity.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.bangtiray.core.database.LocalUserDatabase
import com.bangtiray.core.database.entity.LocalUser
import com.bangtiray.core.network.AuthInstanceNetwork
import com.bangtiray.core.network.AuthNetwork
import org.koin.android.ext.android.inject
import org.koin.androidx.viewmodel.ext.android.viewModel
import com.bangtiray.core.di.authNetwork
import com.bangtiray.core.ui.base.BaseActivity
import com.bangtiray.core.ui.viewmodel.AuthViewModel
import com.bangtiray.core.utils.logi
import com.google.gson.Gson
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MainActivity : BaseActivity() {
    private lateinit var username:EditText
    private lateinit var password:EditText
    private lateinit var btnLogin:Button
    private val viewModel:AuthViewModel by viewModel()
    private val localUserDb:LocalUserDatabase by inject()
    private val authNetwork:AuthNetwork by authNetwork()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username=findViewById(R.id.username)
        password=findViewById(R.id.password)
        btnLogin=findViewById(R.id.button)
        val devToken="dZrAs8z5T-GNn8WXHW4sC2:APA91bHdWwasOKyFUhvcCpMzaxQ0GDSftVY4xqLqnxIob83xqF3rUTdu9nXr_BYWJIPosIcK-7f2d3T4B-dBQPICPPYwBdOOZc0J7bmRssT-mDJyEhMK5tfkVDp0zBxIN0omR1kJ1x0M"
        btnLogin.setOnClickListener {
            //viewModel.login(this, "${username.text}","${password.text}", devToken)
            authNetwork.login(this, "${username.text}","${password.text}", devToken, object : AuthNetwork.AuthNetworkCallback {
                override fun onSuccess(localUser: LocalUser) {
                    logi(localUser.first_name)

                    GlobalScope.launch {
                        localUserDb.localUserDao().insert(localUser)
                    }
                }

                override fun onFailed(message: String?) {
                    logi(message)
                }

            })
        }
    }
}