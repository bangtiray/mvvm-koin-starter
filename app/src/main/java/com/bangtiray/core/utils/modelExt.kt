package com.bangtiray.core.utils

import com.bangtiray.core.database.entity.LocalUser

fun LocalUser.toDataUser():LocalUser{
    var id=id
    var message=""
    var name= first_name
    var picture=picture
    var photo=photo
    var token=token
    var address=address
    return LocalUser(id, first_name, picture, photo, address, token, message)
}