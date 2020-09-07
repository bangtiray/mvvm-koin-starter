package com.bangtiray.core.data.repository

import com.bangtiray.core.database.LocalNewsDatabase
import com.bangtiray.core.database.entity.LocalNews

class NewsRepository(private val localNewsDatabase: LocalNewsDatabase) {
    fun loadNews() = localNewsDatabase.localNewsDao().loadNews()
    suspend fun insert(localNews: List<LocalNews>) = localNewsDatabase.localNewsDao().insert(localNews)


}