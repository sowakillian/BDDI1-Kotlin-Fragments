package com.gmail.killian.news.repositories

import com.gmail.killian.news.BuildConfig
import com.gmail.killian.news.model.Article
import com.gmail.killian.news.services.ArticleService
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

class ArticleRepository {
    private val service: ArticleService
    init {
        val client = OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().apply {
                level = if (BuildConfig.DEBUG) {
                    HttpLoggingInterceptor.Level.BODY
                } else {
                    HttpLoggingInterceptor.Level.NONE
                }
            })
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .build()

        val retrofit = Retrofit.Builder().apply {
            baseUrl("https://newsapi.org")
        }.addConverterFactory(GsonConverterFactory.create())
            .client(client)
            .build()
        service = retrofit.create(ArticleService::class.java)
    }
    fun list(q: String): List<Article> {
        val response = service.list(q).execute()
        return response.body() ?.articles ?: emptyList()
    }
}