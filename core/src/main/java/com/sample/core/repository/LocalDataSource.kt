package com.sample.core.repository

import io.reactivex.Single

interface LocalDataSource<T> {

    fun save(t: T)

    fun get(): Single<T>

}