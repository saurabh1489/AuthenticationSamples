package com.sample.core.usecase

import com.sample.core.data.File
import com.sample.core.repository.NetworkParams
import com.sample.core.repository.Repository

class GetFiles(private val driveRepository: Repository<List<File>>) : UseCase<List<File>> {
    override operator fun invoke(networkParams: NetworkParams?) =
        driveRepository.getAndCache(networkParams)
}