package com.sample.authenticationsamples.framework.usecase

import com.sample.core.usecase.GetNewSession
import com.sample.core.usecase.GetSavedSession

data class SessionUseCase(
    val getSavedSession: GetSavedSession,
    val getNewSession: GetNewSession
)