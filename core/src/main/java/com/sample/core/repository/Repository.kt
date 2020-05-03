package com.sample.core.repository

import io.reactivex.Flowable
import io.reactivex.Single

class Repository<T>(
    private val remoteDataSource: RemoteDataSource<T>,
    private val localDataSource: LocalDataSource<T>
) {
    fun getAndCache(networkParams: NetworkParams?): Flowable<T> {
        return remoteDataSource.fetch(networkParams)
            .doOnSuccess {
                localDataSource.save(it)
            }
            .doOnError {
                throw Exception("Error happened while fetching session info")
            }
            .toFlowable()
            .startWith(getCached())
    }

    fun getCached(): T? {
        return localDataSource.get()
    }

    fun save(t: T) {
        localDataSource.save(t)
    }
}