package com.example.practise2023.domain.usecases

import androidx.paging.PagingData
import com.example.practise2023.domain.model.Article
import com.example.practise2023.domain.repository.NewsRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetNews @Inject constructor(
    private val newsRepository: NewsRepository
) {
    operator fun invoke(sources: List<String>): Flow<PagingData<Article>> {
        return newsRepository.getNews(sources = sources)
    }
}