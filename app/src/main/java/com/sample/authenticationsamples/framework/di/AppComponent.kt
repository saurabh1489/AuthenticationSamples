package com.sample.authenticationsamples.framework.di

import android.content.Context
import com.sample.authenticationsamples.AuthenticationSamplesApp
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        ActivityModule::class
    ]
)
interface AppComponent : AndroidInjector<AuthenticationSamplesApp> {

    override fun inject(application: AuthenticationSamplesApp)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun bindContext(context: Context): Builder
        fun build(): AppComponent
    }

}