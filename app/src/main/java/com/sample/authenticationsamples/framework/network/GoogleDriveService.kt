package com.sample.authenticationsamples.framework.network

import com.sample.authenticationsamples.BuildConfig
import com.sample.authenticationsamples.framework.network.domain.ListFileResponse
import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers

const val GOOGLE_DRIVE_ENDPOINT = "https://www.googleapis.com/"

interface GoogleDriveService {

    @Headers("Accept: application/json")
    @GET("drive/v3/files?key=${BuildConfig.GOOGLE_DRIVE_API_KEY}")
    fun getFiles(): Single<ListFileResponse>
}