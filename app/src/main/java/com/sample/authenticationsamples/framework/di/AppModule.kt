package com.sample.authenticationsamples.framework.di

import android.util.Log
import com.sample.authenticationsamples.framework.network.AuthenticationService
import com.sample.authenticationsamples.framework.network.GOOGLE_AUTH_ENDPOINT
import com.sample.authenticationsamples.framework.network.GOOGLE_DRIVE_ENDPOINT
import com.sample.authenticationsamples.framework.network.GoogleDriveService
import com.sample.core.data.Session
import com.sample.core.repository.Repository
import dagger.Module
import dagger.Provides
import okhttp3.*
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
    fun provideGoogleDriveService(authenticationService: AuthenticationService, repository: Repository<Session>): GoogleDriveService {
        val interceptor = HttpLoggingInterceptor().apply {
            setLevel(HttpLoggingInterceptor.Level.BODY)
        }
        val okHttpClient = OkHttpClient.Builder()
            .readTimeout(10, TimeUnit.SECONDS)
            .authenticator(object : Authenticator {
                override fun authenticate(route: Route?, response: Response): Request? {
                    Log.d("Awasthi","authenticate " + response.code)
                    if (response.code == 401) {
                        repository.getCached()?.refreshToken?.let{
                            val sessionResponse = authenticationService.refreshAccessToken(refreshToken = it).blockingGet()
                            repository.save(Session(sessionResponse.accessToken, it))
                        }
                        return response.request.newBuilder()
                            .header("Authorization", "Bearer ${repository.getCached()?.accessToken}")
                            .build();
                    } else {
                        return null
                    }
                }
            })
            .addInterceptor(interceptor)
            .addInterceptor { chain ->
                val request = chain.request()
                    .newBuilder()
                    .addHeader("Authorization", "Bearer ${repository.getCached()?.accessToken}")
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