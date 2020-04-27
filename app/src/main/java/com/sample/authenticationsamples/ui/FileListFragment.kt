package com.sample.authenticationsamples.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import com.sample.authenticationsamples.framework.viewmodel.FileListViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class FileListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val fileListViewModel by viewModels<FileListViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        observeFileList()
    }

    private fun observeFileList() {
        fileListViewModel.fileListLiveData.observe(viewLifecycleOwner, Observer {
            it.forEach {
                Log.d("Awasthi", "Files : ${it.title}");
            }
        })
    }

    companion object {
        fun instance(): FileListFragment {
            return FileListFragment()
        }
    }

}