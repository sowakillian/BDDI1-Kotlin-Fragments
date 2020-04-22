package com.gmail.killian.news.services

import com.gmail.killian.news.model.ArticleResult
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ArticleService {
    @GET("/v2/everything")
    fun list(@Query("q") q: String?): Call<ArticleResult>
}

