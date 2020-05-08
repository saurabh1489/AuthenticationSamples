package com.sample.authenticationsamples.framework.db.vo

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.sample.core.data.File

@Entity(tableName = "file")
data class FileEntity(
    @ColumnInfo(name = "Title") val title: String,
    @ColumnInfo(name = "Mime Type") val mimeType: String,
    @PrimaryKey(autoGenerate = true) val id: Long = 0L
) {
    fun toFile() = File(title, mimeType)

    companion object {
        fun fromFile(file: File) = FileEntity(file.name, file.mimeType)
    }
}