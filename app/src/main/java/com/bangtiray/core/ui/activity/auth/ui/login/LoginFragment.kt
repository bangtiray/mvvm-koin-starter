package com.bangtiray.core.ui.activity.auth.ui.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.activity.addCallback
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.bangtiray.core.database.LocalUserDatabase
import com.bangtiray.core.di.authNetwork
import com.bangtiray.core.network.auth.AuthNetwork
import com.bangtiray.core.ui.activity.auth.R
import com.bangtiray.core.utils.logi
import org.koin.android.ext.android.inject

class LoginFragment : Fragment() {


    private val viewModel: LoginViewModel by activityViewModels()
    private val localUserDb: LocalUserDatabase by inject()
    private val authNetwork: AuthNetwork by authNetwork()

    private lateinit var usernameEditText: EditText
    private lateinit var passwordEditText: EditText
    private lateinit var loginButton: Button

    companion object {
        fun newInstance() = LoginFragment()
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.login_fragment, container, false)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        usernameEditText = view.findViewById(R.id.username_edit_text)
        passwordEditText = view.findViewById(R.id.password_edit_text)

        loginButton = view.findViewById(R.id.login_button)
        loginButton.setOnClickListener {
            viewModel.authenticate(usernameEditText.text.toString(),
                passwordEditText.text.toString(), localUserDb, authNetwork, this)
        }
        val navController = findNavController()
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner) {
            viewModel.refuseAuthentication()
            navController.popBackStack(R.id.navigation_home, false)
        }


        viewModel.authenticationState.observe(viewLifecycleOwner, Observer { authenticationState ->
            when (authenticationState) {
                LoginViewModel.AuthenticationState.AUTHENTICATED -> navController.popBackStack()
                LoginViewModel.AuthenticationState.INVALID_AUTHENTICATION -> showErrorMessage()
            }
        })
    }

    private fun showErrorMessage() {
        logi("Invalid Login")
    }

}