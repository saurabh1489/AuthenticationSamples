package com.sample.authenticationsamples.framework.datasource.session

import android.content.Context
import android.content.SharedPreferences
import android.util.Log
import com.sample.core.data.Session
import com.sample.core.repository.LocalDataSource
import io.reactivex.Single
import javax.inject.Inject

class SessionLocalDataSource @Inject constructor(context: Context) :
    LocalDataSource<Session> {

    private val prefs: SharedPreferences = context.getSharedPreferences(
        context.packageName,
        Context.MODE_PRIVATE
    )

    companion object {
        const val KEY_ACCESS_TOKEN = "access_token"
        const val KEY_REFRESH_TOKEN = "refresh_token"
    }

    override fun save(session: Session) {
        prefs.edit().putString(KEY_ACCESS_TOKEN, session.accessToken).apply()
        prefs.edit().putString(KEY_REFRESH_TOKEN, session.refreshToken).apply()
    }

    override fun get() = Single.just(
        Session(
            prefs.getString(KEY_ACCESS_TOKEN, null),
            prefs.getString(KEY_REFRESH_TOKEN, null)
        )
    )
}