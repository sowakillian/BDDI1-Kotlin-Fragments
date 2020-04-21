package com.gmail.killian.news.model

data class ArticleResult (
    val status: String,
    val totalResult: Int,
    val articles: List<Article>
)

data class Article(
    val author: String,
    val source: Source,
    val url: String,
    val urlToImage: String,
    val publishedAt: String,
    val title: String,
    val description:String,
    val content: String
)

data class Source(
    val id: String,
    val name: String
)