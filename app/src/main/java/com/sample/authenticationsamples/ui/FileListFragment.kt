package com.sample.authenticationsamples.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.sample.authenticationsamples.R
import java.io.File

class FileListFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_list, null, false)
    }

    companion object {
        fun instance(): FileListFragment {
            return FileListFragment()
        }
    }

}