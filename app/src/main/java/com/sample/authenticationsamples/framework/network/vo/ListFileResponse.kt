package com.sample.authenticationsamples.framework.network.vo


import com.google.gson.annotations.SerializedName

data class ListFileResponse(
    @SerializedName("files")
    val fileSchemas: List<FileSchema>,
    @SerializedName("incompleteSearch")
    val incompleteSearch: Boolean,
    @SerializedName("kind")
    val kind: String,
    @SerializedName("nextPageToken")
    val nextPageToken: String
)