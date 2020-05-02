package com.sample.authenticationsamples.framework.viewmodel

import androidx.lifecycle.*
import com.sample.authenticationsamples.BuildConfig
import com.sample.authenticationsamples.framework.usecase.SessionUseCase
import com.sample.authenticationsamples.ui.login.REDIRECT_URL
import com.sample.authenticationsamples.util.Authentication
import com.sample.core.repository.NetworkParams
import javax.inject.Inject

class LoginViewModel @Inject constructor(
    private val sessionUseCase: SessionUseCase
) : ViewModel() {

    private val authCode = MutableLiveData<String>()
    private val loginState = MediatorLiveData<Boolean>()
    val loginStateLiveData: LiveData<Boolean>
        get() = loginState

    init {
        loginState.value = sessionUseCase.getSavedSession(null).blockingGet().accessToken != null
        loginState.addSource(authCode) { code ->
            sessionUseCase.getNewSession(createAuthenticationNetworkParams(code))
                .map {
                    loginState.postValue(true)
                }.onErrorReturn {
                    loginState.postValue(false)
                }.subscribe()
        }
    }

    fun setAuthCode(code: String) {
        authCode.value = code
    }

    private fun createAuthenticationNetworkParams(authCode: String): NetworkParams {
        return object : NetworkParams {
            override fun getQueryParams(): Map<String, String> {
                val params = mutableMapOf<String, String>()
                params["client_id"] = BuildConfig.CLIENT_ID
                params["code"] = authCode
                params["code_verifier"] = Authentication.getCodeVerifier(cached = true)
                params["grant_type"] = "authorization_code"
                params["redirect_uri"] =
                    REDIRECT_URL
                return params.toMap()
            }
        }
    }
}