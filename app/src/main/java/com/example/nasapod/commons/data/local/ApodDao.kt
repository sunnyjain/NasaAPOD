package com.example.nasapod.commons.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ApodDao {

    /*this will limit the query to return 20 records from the last offset limit.*/
    @Query("SELECT * FROM APODObject where id >= :startIndex and id < :endIndex ORDER BY Date(date) DESC")
    fun getAPODList(startIndex: Long, endIndex: Long): Flowable<List<APODObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAPODs(apods: List<APODObject>)

    @Query("SELECT date FROM APODObject ORDER BY Date(date) ASC LIMIT 1")
    fun getMinDateAvailable(): Single<String>

    @Query("SELECT id FROM APODObject ORDER BY id DESC LIMIT 1")
    fun getLastRecordId(): Single<Long>
}
