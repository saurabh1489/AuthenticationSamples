package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.framework.datasource.drive.DriveLocalDataSource
import com.sample.authenticationsamples.framework.datasource.drive.DriveRemoteDataSource
import com.sample.authenticationsamples.framework.datasource.session.SessionLocalDataSource
import com.sample.authenticationsamples.framework.datasource.session.SessionRemoteDataSource
import com.sample.core.data.File
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.RemoteDataSource
import dagger.Binds
import dagger.Module

@Module
abstract class DataSourceModule {
    @Binds
    abstract fun sessionLocalDataSource(sessionLocalDataSource: SessionLocalDataSource): LocalDataSource<Session>

    @Binds
    abstract fun sessionRemoteDataSource(sessionRemoteSource: SessionRemoteDataSource): RemoteDataSource<Session>

    @Binds
    abstract fun driveLocalDataSource(driveLocalSource: DriveLocalDataSource): LocalDataSource<List<File>>

    @Binds
    abstract fun driveRemoteDataSource(driveRemoteSource: DriveRemoteDataSource): RemoteDataSource<List<File>>
}