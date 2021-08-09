package com.example.myout.repositories

import com.example.myout.roomDB.dao.RunDao
import com.example.myout.roomDB.entities.Run
import javax.inject.Inject

class MainRepository @Inject constructor(
    val runDao: RunDao
) {

    suspend fun insertRun(run: Run) = runDao.insert(run)

    suspend fun deleteRun(run: Run) = runDao.delete(run)

    fun getAllRunsSortedByDate() = runDao.getAllRunsSortedbyDate()

    fun getAllRunsSortedByDistance() = runDao.getAllRunsSortedbyDistance()

    fun getAllRunsSortedByTimeInMillis() = runDao.getAllRunsSortedbyTimeInMillis()

    fun getAllRunsSortedByAverageSpeed() = runDao.getAllRunsSortedbyAverageSpeed()

    fun getAllRunsSortedByCaloriesBurned() = runDao.getAllRunsSortedbyCaloriesBurned()



    fun getTotalAverageSpeed() = runDao.getTotalAverageSpeed()

    fun getTotalCaloriesBurned() = runDao.getTotalCaloriesBurned()

    fun getTotalDistance() = runDao.getTotalDistance()

    fun getTotalTimeInMillis() = runDao.getTotalTimeInMillis()

}