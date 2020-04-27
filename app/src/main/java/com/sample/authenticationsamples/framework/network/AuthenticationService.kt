package com.sample.authenticationsamples.framework.network

import com.sample.authenticationsamples.framework.network.domain.SessionResponse
import io.reactivex.Single
import retrofit2.http.POST
import retrofit2.http.Query

const val GOOGLE_AUTH_ENDPOINT = "https://oauth2.googleapis.com/"

interface AuthenticationService {

    @POST("token")
    fun requestAccessToken(
        @Query("client_id") clientId: String,
        @Query("code") code: String,
        @Query("code_verifier") codeVerifier: String,
        @Query("grant_type") grantType: String,
        @Query("redirect_uri") redirectUrl: String
    ): Single<SessionResponse>

}