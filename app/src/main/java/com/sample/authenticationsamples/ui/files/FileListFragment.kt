package com.sample.authenticationsamples.ui.files

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.authenticationsamples.R
import com.sample.authenticationsamples.framework.provider.ViewModelFactory
import com.sample.authenticationsamples.framework.viewmodel.FileListViewModel
import com.sample.authenticationsamples.ui.files.ViewStatus.*
import com.sample.core.data.File
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_list.*
import javax.inject.Inject

class FileListFragment : DaggerFragment() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory
    private lateinit var adapter: FileAdapter

    private val fileListViewModel by viewModels<FileListViewModel> {
        viewModelFactory
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setupRecyclerView()
        observeFileList()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))
        adapter = FileAdapter()
        recyclerView.adapter = adapter
    }

    private fun observeFileList() {
        fileListViewModel.fileListLiveData.observe(viewLifecycleOwner, Observer { viewStatus ->
            when (viewStatus.status) {
                LOADING -> showLoading()
                ERROR -> showError()
                SUCCESS -> showFileList(viewStatus.data)
            }
        })
    }

    private fun showFileList(files: List<File>?) {
        loadingView.visibility = GONE
        adapter.submitList(files)
    }

    private fun showError() {
        loadingView.visibility = GONE
        Toast.makeText(
            context,
            "Error while fetching files. Please try again later!",
            Toast.LENGTH_SHORT
        ).show()
    }

    private fun showLoading() {
        loadingView.visibility = VISIBLE
    }

    companion object {
        fun instance(): FileListFragment {
            return FileListFragment()
        }
    }

}