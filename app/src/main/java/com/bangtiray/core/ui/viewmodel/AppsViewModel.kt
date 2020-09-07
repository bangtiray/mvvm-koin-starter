package com.bangtiray.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangtiray.core.data.repository.AppsRepository
import com.bangtiray.core.database.entity.LocalApps
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class AppsViewModel(private val appsRepository: AppsRepository) : ViewModel() {
    val apps = appsRepository.loadApps()
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)

    suspend fun insert(localApps: List<LocalApps>) = appsRepository.insert(localApps)
}
