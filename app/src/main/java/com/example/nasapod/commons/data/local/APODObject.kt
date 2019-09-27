package com.example.nasapod.commons.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import javax.annotation.Nullable


@Entity
data class APODObject(

    @SerializedName("date") @PrimaryKey val date: String,
    @SerializedName("explanation") var explanation: String = "",
    @Nullable @SerializedName("hdurl") var hdurl: String?,
    @SerializedName("media_type") var mediaType: String = "",
    @SerializedName("service_version") var serviceVersion: String = "",
    @SerializedName("title") var title: String = "",
    @SerializedName("url") var tileImageUrl: String = ""
)
