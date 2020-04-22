package com.sample.authenticationsamples.framework.datasource

import com.sample.authenticationsamples.BuildConfig
import com.sample.authenticationsamples.framework.network.AuthenticationService
import com.sample.authenticationsamples.ui.REDIRECT_URL
import com.sample.authenticationsamples.util.Authentication
import com.sample.core.data.Session
import com.sample.core.repository.NetworkParams
import com.sample.core.repository.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class SessionRemoteSource @Inject constructor(
    private val authenticationService: AuthenticationService
) : RemoteDataSource<Session> {

    override fun fetch(networkParams: NetworkParams): Single<Session> {
        val queryParams = networkParams.getQueryParams()
        return authenticationService.requestAccessToken(
            queryParams.get("client_id").toString(),
            queryParams.get("code").toString(),
            queryParams.get("code_verifier").toString(),
            queryParams.get("grant_type").toString(),
            queryParams.get("redirect_uri").toString()
        ).map { it.toSession() }
    }
}