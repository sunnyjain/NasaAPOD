package com.example.nasapod.commons.testing

import androidx.annotation.VisibleForTesting
import com.example.nasapod.commons.data.local.APODObject


@VisibleForTesting(otherwise = VisibleForTesting.NONE)
object SampleRecords {
    fun APODObject(id: Long) = APODObject(id, "date$id", "explanation$id",
        "hdurl$id", "mediaType$id", "serviceVersion$id", "title$id", "tileImageUrl$id")
}