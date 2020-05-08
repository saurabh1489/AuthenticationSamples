package com.sample.authenticationsamples.framework.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.sample.authenticationsamples.framework.db.vo.FileEntity
import io.reactivex.Single

@Dao
abstract class FileListDao {

    @Query("SELECT * FROM file")
    abstract fun get(): Single<List<FileEntity>>

    @Insert
    abstract fun insert(files: List<FileEntity>)
}