package com.gmail.killian.news.repositories

import com.gmail.killian.news.model.Article
import com.gmail.killian.news.services.ArticleService
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class ArticleRepository {
    private val service: ArticleService
    init {
        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
        }.addConverterFactory(GsonConverterFactory.create())
            .build()
        service = retrofit.create(ArticleService::class.java)
    }
    fun list(q: String): List<Article> {
        val response = service.list(q).execute()
        return response.body() ?.articles ?: emptyList()
    }
}