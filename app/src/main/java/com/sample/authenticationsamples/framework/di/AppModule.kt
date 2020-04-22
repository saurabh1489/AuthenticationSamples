package com.sample.authenticationsamples.framework.di

import android.util.Log
import com.sample.authenticationsamples.framework.network.AuthenticationService
import com.sample.authenticationsamples.framework.network.GOOGLE_AUTH_ENDPOINT
import com.sample.authenticationsamples.framework.usecase.SessionUseCase
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.RemoteDataSource
import com.sample.core.repository.Repository
import com.sample.core.usecase.GetNewSession
import com.sample.core.usecase.GetSavedSession
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, SessionModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService {
        return Retrofit.Builder()
            .baseUrl(GOOGLE_AUTH_ENDPOINT)
            .client(okHttpClient())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthenticationService::class.java)
    }

    private fun okHttpClient(): OkHttpClient {
        val interceptor = HttpLoggingInterceptor().run {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        return OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                Log.d("Awasthi", "Request=${request.body}")
                chain.proceed(request)
            }
            .build()
    }

    @Provides
    fun provideUseCases(repository: Repository<Session>) = SessionUseCase(
        GetSavedSession(repository),
        GetNewSession(repository)
    )

    @Provides
    fun provideSessionRepository(
        remoteDataSource: RemoteDataSource<Session>,
        localDataSource: LocalDataSource<Session>
    ) = Repository(remoteDataSource, localDataSource)

}