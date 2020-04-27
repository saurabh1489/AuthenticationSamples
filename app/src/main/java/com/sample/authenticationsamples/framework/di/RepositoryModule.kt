package com.sample.authenticationsamples.framework.di

import com.sample.core.data.File
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.RemoteDataSource
import com.sample.core.repository.Repository
import dagger.Module
import dagger.Provides

@Module
class RepositoryModule {

    @Provides
    fun provideSessionRepository(
        remoteDataSource: RemoteDataSource<Session>,
        localDataSource: LocalDataSource<Session>
    ) = Repository(remoteDataSource, localDataSource)

    @Provides
    fun provideDriveRepository(
        remoteDataSource: RemoteDataSource<List<File>>,
        localDataSource: LocalDataSource<List<File>>
    ) = Repository(remoteDataSource, localDataSource)

}