package com.example.nasapod.commons.data.local

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [APODObject::class], version = 2, exportSchema =  false)
abstract class ApodDB : RoomDatabase() {
    abstract fun apodDao(): ApodDao
}