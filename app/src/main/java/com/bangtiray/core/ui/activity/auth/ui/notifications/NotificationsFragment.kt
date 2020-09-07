package com.bangtiray.core.ui.activity.auth.ui.notifications

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.findNavController
import com.bangtiray.core.ui.activity.auth.R
import com.bangtiray.core.ui.activity.auth.ui.login.LoginViewModel
import com.bangtiray.core.ui.activity.auth.ui.login.LoginViewModel.AuthenticationState.UNAUTHENTICATED
import com.bangtiray.core.ui.activity.auth.ui.login.LoginViewModel.AuthenticationState.AUTHENTICATED
import com.bangtiray.core.ui.activity.auth.ui.login.LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION
import com.bangtiray.core.utils.logi

class NotificationsFragment : Fragment() {

  private lateinit var notificationsViewModel: NotificationsViewModel
  private val viewModel: LoginViewModel by activityViewModels()
  override fun onCreateView(
    inflater: LayoutInflater,
    container: ViewGroup?,
    savedInstanceState: Bundle?
  ): View? {
    notificationsViewModel =
    ViewModelProviders.of(this).get(NotificationsViewModel::class.java)
    val root = inflater.inflate(R.layout.fragment_notifications, container, false)
//    val textView: TextView = root.findViewById(R.id.text_notifications)
//    notificationsViewModel.text.observe(viewLifecycleOwner, Observer {
//      textView.text = it
//    })


    val navController=findNavController()
    viewModel.authenticationState.observe(viewLifecycleOwner, Observer { auth ->
      when(auth){
        AUTHENTICATED -> showWelcomeMessage()
        UNAUTHENTICATED -> navController.navigate(R.id.login_fragment)
      }
    })
    return root
  }

  private fun showWelcomeMessage() {
    logi("Masuk nih")
  }
}