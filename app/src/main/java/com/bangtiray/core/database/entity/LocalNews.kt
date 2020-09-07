package com.bangtiray.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "local_news")
@Parcelize
data class LocalNews(
    @PrimaryKey var id: Int,
    var name: String,
    var featured_image: String,
    var slug: String,
    var content: String,
    var publish_datetime: String,
    var user_name: String
) : Parcelable