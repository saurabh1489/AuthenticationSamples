package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.ui.LoginActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilderModule {

    @ContributesAndroidInjector
    abstract fun contributeLoginActivity(): LoginActivity

}