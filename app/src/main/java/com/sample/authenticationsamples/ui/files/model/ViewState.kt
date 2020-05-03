package com.sample.authenticationsamples.ui.files.model

import com.sample.authenticationsamples.ui.files.ViewStatus

data class ViewState<T>(val status: ViewStatus, val data: T? = null)