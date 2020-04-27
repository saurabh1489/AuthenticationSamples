package com.sample.core.usecase

import com.sample.core.data.Session
import com.sample.core.repository.NetworkParams
import com.sample.core.repository.Repository

class GetNewSession(private val sessionRepository: Repository<Session>) : UseCase<Session> {
    override operator fun invoke(networkParams: NetworkParams?) =
        sessionRepository.getAndCache(networkParams)
}