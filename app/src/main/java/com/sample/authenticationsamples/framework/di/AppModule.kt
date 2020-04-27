package com.sample.authenticationsamples.framework.di

import com.sample.authenticationsamples.framework.network.AuthenticationService
import com.sample.authenticationsamples.framework.network.GOOGLE_AUTH_ENDPOINT
import com.sample.authenticationsamples.framework.network.GOOGLE_DRIVE_ENDPOINT
import com.sample.authenticationsamples.framework.network.GoogleDriveService
import com.sample.core.data.Session
import com.sample.core.repository.Repository
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

@Module(includes = [ViewModelModule::class, DataSourceModule::class, UseCaseModule::class, RepositoryModule::class])
class AppModule {

    @Provides
    @Singleton
    fun provideAuthenticationService(): AuthenticationService {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .build()
        return Retrofit.Builder()
            .baseUrl(GOOGLE_AUTH_ENDPOINT)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(AuthenticationService::class.java)
    }

    @Provides
    @Singleton
    fun provideGoogleDriveService(repository: Repository<Session>): GoogleDriveService {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${repository.getCached().accessToken}")
                    .build()
                chain.proceed(request)
            }
            .build()
        return Retrofit.Builder()
            .baseUrl(GOOGLE_DRIVE_ENDPOINT)
            .client(okHttpClient)
            .addCallAdapterFactory(RxJava2CallAdapterFactory.createAsync())
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(GoogleDriveService::class.java)
    }

}