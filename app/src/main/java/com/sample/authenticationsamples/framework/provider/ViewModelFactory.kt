package com.sample.authenticationsamples.framework.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.sample.authenticationsamples.framework.usecase.SessionUseCase
import com.sample.authenticationsamples.framework.viewmodel.LoginViewModel
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ViewModelFactory @Inject constructor(
    private val useCase: SessionUseCase
) : ViewModelProvider.Factory {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(
                useCase
            ) as T
        }
        throw IllegalStateException("ViewModel class not recognized")
    }
}