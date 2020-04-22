package com.sample.core.repository

interface NetworkParams {
    fun getQueryParams(): Map<String, String>
}