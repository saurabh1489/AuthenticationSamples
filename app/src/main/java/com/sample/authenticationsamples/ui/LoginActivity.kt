package com.sample.authenticationsamples.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import com.sample.authenticationsamples.framework.viewmodel.LoginViewModel
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class LoginActivity : DaggerAppCompatActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val loginViewModel by viewModels<LoginViewModel> {
        viewModelFactory
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        intent?.let {
            parseIntent(it)
        }
        setContentView(R.layout.activity_main)
        observeLoginState()
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

    private fun observeLoginState() {
        loginViewModel.loginStateLiveData.observe(this, Observer { status ->
            if (status) {
                Log.d("Awasthi", "Login successful")
                replaceFragment(FileListFragment.instance())
            } else {
                Log.d("Awasthi", "No Login")
                replaceFragment(LoginFragment.instance())
            }
        })
    }

    override fun onNewIntent(intent: Intent?) {
        super.onNewIntent(intent)
        intent?.let {
            parseIntent(it)
        }
    }

    private fun parseIntent(intent: Intent) {
        val uri = intent.data
        val path = uri?.path
        when(path) {
            "/oauth2redirect" -> {
                val authCode = uri?.getQueryParameters("code")
                authCode?.run {
                    Log.d("LoginActivity", "AuthCode $authCode")
                    loginViewModel.setAuthCode(this[0])
                }
            }
        }
    }
}