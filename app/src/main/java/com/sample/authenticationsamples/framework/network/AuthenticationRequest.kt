package com.sample.authenticationsamples.framework.network

import com.sample.authenticationsamples.BuildConfig
import com.sample.authenticationsamples.ui.login.REDIRECT_URL
import com.sample.authenticationsamples.util.Authentication
import okhttp3.HttpUrl.Companion.toHttpUrlOrNull

const val AUTH_CODE_ENDPOINT = "https://accounts.google.com/o/oauth2/v2/auth"

class AuthenticationRequest {

    companion object {
        fun authCodeRequest(state: String) =
            AUTH_CODE_ENDPOINT.toHttpUrlOrNull()?.newBuilder()?.run {
                addQueryParameter("client_id", BuildConfig.CLIENT_ID)
                addQueryParameter("scope", "https://www.googleapis.com/auth/drive")
                addQueryParameter("redirect_uri",
                    REDIRECT_URL
                )
                addQueryParameter("response_type", "code")
                addQueryParameter(
                    "code_challenge",
                    Authentication.getCodeChallenge(Authentication.getCodeVerifier(cached = false))
                )
                addQueryParameter("code_challenge_method", "S256")
                addQueryParameter("state", state)
                build()
            }?.toUrl()
    }

}