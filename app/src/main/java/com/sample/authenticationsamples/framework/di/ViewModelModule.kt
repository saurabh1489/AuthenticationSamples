package com.sample.authenticationsamples.framework.di

import androidx.lifecycle.ViewModelProvider
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import dagger.Binds
import dagger.Module

@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}