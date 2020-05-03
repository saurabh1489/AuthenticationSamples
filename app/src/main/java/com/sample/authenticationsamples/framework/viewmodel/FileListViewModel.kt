package com.sample.authenticationsamples.framework.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.sample.authenticationsamples.framework.usecase.DriveUseCase
import com.sample.authenticationsamples.ui.files.ViewStatus
import com.sample.authenticationsamples.ui.files.model.ViewState
import com.sample.authenticationsamples.util.toLiveData
import com.sample.core.data.File
import javax.inject.Inject

class FileListViewModel @Inject constructor(
    private val driveUseCase: DriveUseCase
) : ViewModel() {

    val fileListLiveData: LiveData<ViewState<List<File>>>
        get() = driveUseCase.getFiles(null)
            .map {
                ViewState(ViewStatus.SUCCESS, it)
            }
            .onErrorReturn {
                ViewState(ViewStatus.ERROR)
            }
            .startWith(ViewState(ViewStatus.LOADING))
            .toLiveData()
}