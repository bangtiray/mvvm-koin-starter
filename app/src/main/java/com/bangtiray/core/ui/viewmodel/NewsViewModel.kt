package com.bangtiray.core.ui.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import com.bangtiray.core.data.repository.NewsRepository
import com.bangtiray.core.database.entity.LocalNews
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn

class NewsViewModel(private val newsRepository: NewsRepository) : ViewModel() {
    val news = newsRepository.loadNews()
        .flowOn(Dispatchers.IO)
        .asLiveData(viewModelScope.coroutineContext)
    suspend fun insert(localNews: List<LocalNews>) = newsRepository.insert(localNews)

}