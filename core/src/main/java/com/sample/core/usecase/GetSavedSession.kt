package com.sample.core.usecase

import com.sample.core.data.Session
import com.sample.core.repository.NetworkParams
import com.sample.core.repository.Repository
import io.reactivex.Single

class GetSavedSession(private val sessionRepository: Repository<Session>) : UseCase<Session> {
    override operator fun invoke(networkParams: NetworkParams?) =
        Single.just(sessionRepository.getCached())
}