package com.sample.authenticationsamples

import com.sample.authenticationsamples.framework.di.DaggerAppComponent
import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class AuthenticationSamplesApp: DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerAppComponent.builder().bindContext(this).build()
    }
}