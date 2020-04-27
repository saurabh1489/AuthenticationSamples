package com.sample.authenticationsamples.framework.di

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import com.sample.authenticationsamples.framework.viewmodel.FileListViewModel
import com.sample.authenticationsamples.framework.viewmodel.LoginViewModel
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(viewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(FileListViewModel::class)
    abstract fun bindFileListViewModel(viewModel: FileListViewModel): ViewModel

    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory
}