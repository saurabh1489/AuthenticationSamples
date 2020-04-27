package com.sample.authenticationsamples.framework.usecase

import com.sample.core.usecase.GetNewSession
import com.sample.core.usecase.GetSavedSession
import javax.inject.Inject

data class SessionUseCase(
    val getSavedSession: GetSavedSession,
    val getNewSession: GetNewSession
) : UseCase