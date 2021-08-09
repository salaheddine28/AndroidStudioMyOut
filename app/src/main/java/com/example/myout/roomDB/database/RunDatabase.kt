package com.example.myout.roomDB.database

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.myout.roomDB.Converters
import com.example.myout.roomDB.dao.RunDao
import com.example.myout.roomDB.entities.Run


@Database(entities = [Run::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class RunDatabase : RoomDatabase() {

    abstract fun getRunDao(): RunDao

}