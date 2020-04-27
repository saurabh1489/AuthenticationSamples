package com.sample.core.usecase

import com.sample.core.repository.NetworkParams
import io.reactivex.Single

interface UseCase<T> {
    operator fun invoke(networkParams: NetworkParams?): Single<T>
}