package com.sample.authenticationsamples.framework.network.domain


import com.google.gson.annotations.SerializedName
import com.sample.core.data.File

data class FileSchema(
    @SerializedName("id")
    val id: String,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("mimeType")
    val mimeType: String,
    @SerializedName("name")
    val name: String
) {
    fun toFile() = File(name, mimeType)
}