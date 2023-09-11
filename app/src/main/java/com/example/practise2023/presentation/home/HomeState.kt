package com.example.practise2023.presentation.home

import androidx.paging.PagingData
import com.example.practise2023.domain.model.Article
import kotlinx.coroutines.flow.Flow

data class HomeState(
    val isLoading: Boolean = false,

    val articles: Flow<PagingData<Article>>? = null,
    val searchCategory:String = "",

    val categories:List<String> = listOf(
        "all",
        "technology",
        "business",
        "science",
        "sports",
        "entertainment",
        "general",
        "health"
    ),
    val selectedCategoryIndex:Int = 0
)
