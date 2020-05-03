package com.sample.authenticationsamples.util

import androidx.lifecycle.LiveData
import androidx.lifecycle.LiveDataReactiveStreams
import io.reactivex.Flowable
import io.reactivex.Single

fun <T> Single<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this.toFlowable())

fun <T> Flowable<T>.toLiveData(): LiveData<T> = LiveDataReactiveStreams.fromPublisher(this)