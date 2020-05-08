package com.sample.authenticationsamples.framework.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.sample.authenticationsamples.usecase.DriveUseCase
import com.sample.authenticationsamples.ui.files.ViewStatus
import com.sample.authenticationsamples.ui.files.model.ViewState
import com.sample.core.data.File
import io.reactivex.disposables.CompositeDisposable
import javax.inject.Inject

class FileListViewModel @Inject constructor(
    driveUseCase: DriveUseCase
) : ViewModel() {

    private val disposable = CompositeDisposable()
    private val fileListMutableLiveData = MutableLiveData<ViewState<List<File>>>()
    val fileListLiveData: LiveData<ViewState<List<File>>>
        get() = fileListMutableLiveData

    init {
        Log.d("Awasthi", "viewmodel get")
        val subscription = driveUseCase.getFiles(null)
            .map {
                ViewState(ViewStatus.SUCCESS, it)
            }
            .onErrorReturn {
                ViewState(ViewStatus.ERROR)
            }
            .startWith(ViewState(ViewStatus.LOADING))
            .subscribe { fileListLiveData -> fileListMutableLiveData.postValue(fileListLiveData) }
        disposable.add(subscription)
    }


    override fun onCleared() {
        super.onCleared()
        disposable.clear()
    }
}