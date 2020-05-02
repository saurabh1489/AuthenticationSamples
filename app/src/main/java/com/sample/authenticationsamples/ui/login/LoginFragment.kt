package com.sample.authenticationsamples.ui.login

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.framework.network.AuthenticationRequest
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import com.sample.authenticationsamples.framework.viewmodel.LoginViewModel
import com.sample.authenticationsamples.ui.MainActivity
import com.sample.authenticationsamples.ui.files.FileListFragment
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_login.view.*
import javax.inject.Inject

const val REDIRECT_URL = "com.sample.authenticationsamples:/oauth2redirect"
const val STATE = "authCode4Google"

class LoginFragment : DaggerFragment() {

    companion object {
        private const val TAG = "LoginFragment"
        fun instance(): LoginFragment {
            return LoginFragment()
        }
    }

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val loginViewModel by viewModels<LoginViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        activity?.intent?.let {
            parseIntent(it)
        }
        observeLoginState()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_login, null, false)
        view.googleLoginButton.setOnClickListener {
            openBrowser()
        }
        return view
    }

    private fun openBrowser() {
        val i = Intent(Intent.ACTION_VIEW)
        i.data = Uri.parse(AuthenticationRequest.authCodeRequest(STATE).toString())
        startActivity(i)
    }

    private fun observeLoginState() {
        loginViewModel.loginStateLiveData.observe(this, Observer { status ->
            if (status) {
                Log.d("Awasthi", "Login successful")
                (activity as? MainActivity)?.replaceFragment(FileListFragment.instance())
            }
        })
    }

    private fun parseIntent(intent: Intent) {
        val uri = intent.data
        val path = uri?.path
        when (path) {
            "/oauth2redirect" -> {
                val authCode = uri?.getQueryParameters("code")
                authCode?.run {
                    Log.d(TAG, "AuthCode $authCode")
                    loginViewModel.setAuthCode(this[0])
                }
            }
        }
    }

}