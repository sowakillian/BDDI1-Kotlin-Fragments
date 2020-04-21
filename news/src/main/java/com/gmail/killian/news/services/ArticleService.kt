package com.gmail.killian.news.services

import com.gmail.killian.news.model.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/everything?apiKey=ce6a846e8d63468380b8b88d00b8c2ec")
    fun list(@Query("q") q: String?): Call<ArticleResult>
}

