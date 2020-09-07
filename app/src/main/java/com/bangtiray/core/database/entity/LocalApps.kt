package com.bangtiray.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "local_apps")
@Parcelize
data class LocalApps(
    @PrimaryKey var id: Int,
    var name: String,
    var featured_image: String,
    var slug: String,
    var content: String,
    var mini_image: String,
    var category: String,
    var classname: String,
    var product_type: String
):Parcelable