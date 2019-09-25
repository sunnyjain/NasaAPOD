package com.example.nasapod.commons.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName


@Entity
data class APODObject(

    @SerializedName("date") @PrimaryKey val date: String,
    @SerializedName("explanation") var explanation: String,
    @SerializedName("hdurl") var hdurl: String,
    @SerializedName("mediaType") var mediaType: String,
    @SerializedName("serviceVersion") var serviceVersion: String,
    @SerializedName("title") var title: String,
    @SerializedName("url") var tileImageUrl: String
)
