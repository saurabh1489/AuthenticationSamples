package com.sample.authenticationsamples.usecase

import com.sample.core.usecase.GetNewSession
import com.sample.core.usecase.GetSavedSession

data class SessionUseCase(
    val getSavedSession: GetSavedSession,
    val getNewSession: GetNewSession
) : UseCase