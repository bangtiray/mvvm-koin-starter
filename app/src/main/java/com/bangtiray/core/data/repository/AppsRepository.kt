package com.bangtiray.core.data.repository

import com.bangtiray.core.database.LocalAppsDatabase
import com.bangtiray.core.database.entity.LocalApps

class AppsRepository (private val localAppsDatabase: LocalAppsDatabase){
    fun loadApps()=localAppsDatabase.localAppsDao().loadApps()
    suspend fun insert(localApps: List<LocalApps>)=localAppsDatabase.localAppsDao().insert(localApps)
}