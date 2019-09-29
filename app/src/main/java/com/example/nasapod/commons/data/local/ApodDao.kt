package com.example.nasapod.commons.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Maybe
import io.reactivex.Single

@Dao
interface ApodDao {

    /*this will limit the query to return 20 records from the last offset limit.*/
    @Query("SELECT * FROM APODObject where Date(date) >= Date(:startDate) and Date(date) <= Date(:endDate) ORDER BY Date(date) DESC")
    fun getAPODList(startDate: String, endDate: String): Maybe<List<APODObject>>



    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAPODs(apods: List<APODObject>): Single<List<Long>>

    @Query("SELECT date FROM APODObject ORDER BY Date(date) ASC LIMIT 1")
    fun getMinDateAvailable(): Single<String>

    @Query("SELECT * FROM APODObject ORDER BY Date(date) ASC LIMIT 1")
    fun getLastRecordId(): Single<APODObject>

    @Query("SELECT * FROM APODObject where date = :date")
    fun getAPODObject(date: String): Single<APODObject>
}
