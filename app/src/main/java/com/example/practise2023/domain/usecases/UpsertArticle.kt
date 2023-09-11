package com.example.practise2023.domain.usecases

import com.example.practise2023.data.local.NewsDao
import com.example.practise2023.domain.model.Article
import javax.inject.Inject

class UpsertArticle @Inject constructor(
    private val newsDao: NewsDao
) {

    suspend operator fun invoke(article: Article){
        newsDao.upsert(article = article)
    }

}