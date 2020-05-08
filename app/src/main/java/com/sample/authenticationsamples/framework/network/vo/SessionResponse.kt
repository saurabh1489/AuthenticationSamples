package com.sample.authenticationsamples.framework.network.vo

import com.google.gson.annotations.SerializedName
import com.sample.core.data.Session

data class SessionResponse(
    @SerializedName("access_token")
    val accessToken: String,
    @SerializedName("expires_in")
    val expiresIn: Int,
    @SerializedName("token_type")
    val tokenType: String,
    @SerializedName("scope")
    val scope: String,
    @SerializedName("refresh_token")
    val refreshToken: String
) {

    fun toSession() = Session(accessToken, refreshToken)

}