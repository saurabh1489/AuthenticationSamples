package com.sample.authenticationsamples.framework.datasource.drive

import android.util.Log
import com.sample.core.data.File
import com.sample.core.repository.LocalDataSource
import javax.inject.Inject

class DriveLocalDataSource @Inject constructor() :
    LocalDataSource<List<@JvmSuppressWildcards File>> {
    override fun save(fileList: List<File>) {
        Log.d("Awasthi", "DriveLocalDataSource save: $fileList")
    }

    override fun get(): List<File> {
        return emptyList()
    }
}