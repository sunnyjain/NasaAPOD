package com.example.nasapod.commons.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import io.reactivex.Flowable
import io.reactivex.Single

@Dao
interface ApodDao {

    @Query("SELECT * FROM APODObject ORDER BY Date(date) DESC")
    fun getAPODList(): Flowable<List<APODObject>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun saveAPODs(apods: List<APODObject>)

    @Query("SELECT date FROM APODObject ORDER BY Date(date) ASC LIMIT 1")
    fun getMinDateAvailable(): Single<String>
}