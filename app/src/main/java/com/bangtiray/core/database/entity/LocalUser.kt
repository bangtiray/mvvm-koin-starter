package com.bangtiray.core.database.entity

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.android.parcel.Parcelize

@Entity(tableName = "local_user")
@Parcelize
data class LocalUser(
    @PrimaryKey
    var id: Int,
    var first_name: String? = "",
    var picture: String? = "",
    var photo: String? = "",
    var address: String? = "",
    var token: String? = "",
    var message:String?=""
) : Parcelable