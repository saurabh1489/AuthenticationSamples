package com.sample.core.usecase

import com.sample.core.data.Session
import com.sample.core.repository.Repository

class GetSavedSession(private val sessionRepository: Repository<Session>) {
    operator fun invoke() = sessionRepository.getCached()
}