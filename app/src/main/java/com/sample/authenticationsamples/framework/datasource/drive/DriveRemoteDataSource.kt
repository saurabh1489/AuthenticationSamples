package com.sample.authenticationsamples.framework.datasource.drive

import com.sample.authenticationsamples.framework.network.GoogleDriveService
import com.sample.core.data.File
import com.sample.core.repository.NetworkParams
import com.sample.core.repository.RemoteDataSource
import io.reactivex.Single
import javax.inject.Inject

class DriveRemoteDataSource @Inject constructor(
    private val driveService: GoogleDriveService
) : RemoteDataSource<List<@JvmSuppressWildcards File>> {
    override fun fetch(networkParams: NetworkParams?): Single<List<File>> {
        return driveService.getFiles().map { it ->
            it.fileSchemas.map { it.toFile() }.toList()
        }
    }
}