package com.sample.core.repository

import io.reactivex.Single

interface RemoteDataSource<T> {
    fun fetch(networkParams: NetworkParams): Single<T>
}