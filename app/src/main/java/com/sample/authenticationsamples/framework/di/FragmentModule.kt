package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.ui.FileListFragment
import com.sample.authenticationsamples.ui.LoginFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentModule {

    @ContributesAndroidInjector
    abstract fun contributeFileListFragment(): FileListFragment

    @ContributesAndroidInjector
    abstract fun contributeLoginFragment(): LoginFragment
}