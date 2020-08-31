package com.bangtiray.core.ui.base

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.bangtiray.core.ui.activity.auth.R
import com.bangtiray.core.utils.ConnectionLiveData
import com.bangtiray.core.utils.slideNetworkStatus


abstract class BaseActivity : AppCompatActivity() {
    private lateinit var slideDialogNetwork: Dialog
    private var isShowSlide = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        ConnectionLiveData(this).observe(this, Observer { connectionSate ->
            checkSlide(connectionSate)
        })
    }

    private fun checkSlide(connectionSate: Boolean) {
        isShowSlide = if (connectionSate) {
            if (isShowSlide) slideDialogNetwork.dismiss()
            false
        } else {
            showSlideNotConnect()
            true
        }
    }

    private fun showSlideNotConnect() {
        slideDialogNetwork= Dialog(this, R.style.CustomDialogAnimation)
        slideNetworkStatus(slideDialogNetwork)
    }
}