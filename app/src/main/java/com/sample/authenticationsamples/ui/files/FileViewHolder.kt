package com.sample.authenticationsamples.ui.files

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.sample.authenticationsamples.R
import com.sample.core.data.File

class FileViewHolder(view: View): RecyclerView.ViewHolder(view) {

    val name: TextView = view.findViewById(R.id.name)

    fun bind(file: File) {
        name.text = file.name
    }
}