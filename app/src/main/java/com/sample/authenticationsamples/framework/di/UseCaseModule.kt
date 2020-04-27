package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.framework.usecase.DriveUseCase
import com.sample.authenticationsamples.framework.usecase.SessionUseCase
import com.sample.core.data.File
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.RemoteDataSource
import com.sample.core.repository.Repository
import com.sample.core.usecase.GetFiles
import com.sample.core.usecase.GetNewSession
import com.sample.core.usecase.GetSavedSession
import dagger.Module
import dagger.Provides

@Module
class UseCaseModule {
    @Provides
    fun provideSessionUseCases(repository: Repository<Session>) = SessionUseCase(
        GetSavedSession(repository),
        GetNewSession(repository)
    )

    @Provides
    fun provideDriveUseCases(repository: Repository<List<File>>) = DriveUseCase(
        GetFiles(repository)
    )

}