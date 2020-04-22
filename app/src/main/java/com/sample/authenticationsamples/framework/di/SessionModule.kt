package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.framework.datasource.SessionLocalSource
import com.sample.authenticationsamples.framework.datasource.SessionRemoteSource
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class SessionModule {
    @Binds
    abstract fun sessionLocalDataSource(sessionLocalSource: SessionLocalSource): LocalDataSource<Session>

    @Binds
    abstract fun sessionRemoteDataSource(sessionRemoteSource: SessionRemoteSource): RemoteDataSource<Session>
}