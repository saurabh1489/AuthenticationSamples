package com.sample.authenticationsamples.framework.network.domain


import com.google.gson.annotations.SerializedName
import com.sample.core.data.File

data class ListFileResponse(
    @SerializedName("files")
    val files: List<String>,
    @SerializedName("incompleteSearch")
    val incompleteSearch: Boolean,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String
) {
    fun toFileList() = files.map {
        File(it)
    }
}