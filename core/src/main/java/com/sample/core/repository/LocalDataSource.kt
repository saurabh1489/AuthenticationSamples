package com.sample.core.repository

interface LocalDataSource<T> {

    fun save(t: T)

    fun get(): T

}