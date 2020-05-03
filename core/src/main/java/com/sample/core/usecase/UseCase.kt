package com.sample.core.usecase

import com.sample.core.repository.NetworkParams
import io.reactivex.Flowable

interface UseCase<T> {
    operator fun invoke(networkParams: NetworkParams?): Flowable<T>
}