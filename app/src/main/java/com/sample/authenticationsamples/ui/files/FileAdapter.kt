package com.sample.authenticationsamples.ui.files

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncDifferConfig.Builder
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.sample.authenticationsamples.R
import com.sample.core.data.File
import java.util.concurrent.Executors

class FileAdapter : ListAdapter<File, FileViewHolder>(
    Builder(object : DiffUtil.ItemCallback<File>() {
        override fun areItemsTheSame(oldItem: File, newItem: File) = oldItem == newItem

        override fun areContentsTheSame(oldItem: File, newItem: File) = oldItem.name == newItem.name
    }).setBackgroundThreadExecutor(Executors.newSingleThreadExecutor()).build()
) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FileViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.item_file, null)
        return FileViewHolder(view)
    }

    override fun onBindViewHolder(holder: FileViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}