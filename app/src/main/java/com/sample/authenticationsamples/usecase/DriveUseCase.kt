package com.sample.authenticationsamples.usecase

import com.sample.core.usecase.GetFiles

data class DriveUseCase(
    val getFiles: GetFiles
) : UseCase