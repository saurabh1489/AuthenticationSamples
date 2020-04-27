package com.sample.authenticationsamples.framework.viewmodel

import android.util.Log
import androidx.lifecycle.*
import com.sample.authenticationsamples.framework.datasource.drive.DriveLocalDataSource
import com.sample.authenticationsamples.framework.usecase.DriveUseCase
import com.sample.authenticationsamples.util.toLiveData
import com.sample.core.data.File
import com.sample.core.repository.LocalDataSource
import com.sample.core.repository.Repository
import javax.inject.Inject

class FileListViewModel @Inject constructor(
    private val driveUseCase: DriveUseCase
) : ViewModel() {

    val fileListLiveData: LiveData<List<File>>
        get() = driveUseCase.getFiles(null)
            .doOnError {
                Log.d("Awasthi", "Error : ${it.message}")
            }.toLiveData()
}