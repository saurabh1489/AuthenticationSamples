package com.sample.authenticationsamples.framework.datasource.drive

import android.util.Log
import com.bumptech.glide.util.Executors
import com.sample.authenticationsamples.executor.AppExecutors
import com.sample.authenticationsamples.framework.db.dao.FileListDao
import com.sample.authenticationsamples.framework.db.vo.FileEntity
import com.sample.core.data.File
import com.sample.core.repository.LocalDataSource
import io.reactivex.Single
import javax.inject.Inject

class DriveLocalDataSource @Inject constructor(
    private val fileListDao: FileListDao,
    private val executors: AppExecutors
) : LocalDataSource<List<@JvmSuppressWildcards File>> {
    override fun save(fileList: List<File>) {
        Log.d("Awasthi", "DriveLocalDataSource save: $fileList")
        executors.diskIO().execute {
            fileListDao.insert(fileList.map { FileEntity.fromFile(it) })
        }
    }

    override fun get(): Single<List<File>> {
        return fileListDao.get().map { files -> files.map(FileEntity::toFile) }
    }
}