package com.sample.authenticationsamples.ui

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.framework.network.AuthenticationRequest
import kotlinx.android.synthetic.main.fragment_login.view.*

const val REDIRECT_URL = "com.sample.authenticationsamples:/oauth2redirect"
const val STATE = "authCode4Google"

class LoginFragment : Fragment() {

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

    companion object {
        fun instance(): LoginFragment {
            return LoginFragment()
        }
    }

}