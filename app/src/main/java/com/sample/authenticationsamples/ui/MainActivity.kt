package com.sample.authenticationsamples.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.ui.login.LoginFragment
import dagger.android.support.DaggerAppCompatActivity

class MainActivity : DaggerAppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        replaceFragment(LoginFragment.instance())
    }

    fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
            .replace(R.id.fragment_container, fragment)
            .commit()
    }

}