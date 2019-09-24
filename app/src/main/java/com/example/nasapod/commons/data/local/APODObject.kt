package com.example.nasapod.commons.data.local

import androidx.room.Entity


@Entity
data class APODObject(

    val date: String,
    var explanation: String,
    var hdurl: String,
    var mediaType: String,
    var serviceVersion: String,
    var title: String,
    var tileImageUrl: String
)
