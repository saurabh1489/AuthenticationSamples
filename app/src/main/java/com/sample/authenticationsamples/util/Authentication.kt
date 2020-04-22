package com.sample.authenticationsamples.util

import android.util.Base64
import java.lang.IllegalStateException
import java.security.MessageDigest
import java.security.SecureRandom

object Authentication {
    const val MIN_CODE_VERIFIER_ENTROPY = 32
    private var cachedCodeVerifier: String? = null

    private fun getCodeVerifier(): String {
        val secureRandom = SecureRandom()
        val code = ByteArray(MIN_CODE_VERIFIER_ENTROPY)
        secureRandom.nextBytes(code)
        val verifier = Base64.encodeToString(
            code,
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
        cachedCodeVerifier = verifier
        return verifier
    }

    fun getCodeVerifier(cached: Boolean): String {
        if (cached) {
            return cachedCodeVerifier ?: throw IllegalStateException("No cached code verifier")
        }
        return getCodeVerifier()
    }

    fun getCodeChallenge(verifier: String): String {
        val bytes = verifier.toByteArray(Charsets.US_ASCII)
        val messageDigest = MessageDigest.getInstance("SHA-256")
        messageDigest.update(bytes)
        return Base64.encodeToString(
            messageDigest.digest(),
            Base64.URL_SAFE or Base64.NO_WRAP or Base64.NO_PADDING
        )
    }
}