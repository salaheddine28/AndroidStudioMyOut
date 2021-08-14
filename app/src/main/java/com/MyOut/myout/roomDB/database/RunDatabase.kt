package com.MyOut.myout.roomDB.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.MyOut.myout.roomDB.Converters
import com.MyOut.myout.roomDB.dao.RunDao
import com.MyOut.myout.roomDB.entities.Run


@Database(entities = [Run::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RunDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao

}