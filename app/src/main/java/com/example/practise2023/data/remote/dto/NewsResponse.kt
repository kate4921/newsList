package com.example.practise2023.data.remote.dto

import com.example.practise2023.domain.model.Article

data class NewsResponse(
    val articles: List<Article>,
    val status: String,
    val totalResults: Int
)
