package com.sample.authenticationsamples.framework.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.sample.authenticationsamples.framework.db.dao.FileListDao
import com.sample.authenticationsamples.framework.db.vo.FileEntity

@Database(entities = [FileEntity::class], version = 1, exportSchema = false)
abstract class FileDb : RoomDatabase() {

    abstract fun fileListDao(): FileListDao

}