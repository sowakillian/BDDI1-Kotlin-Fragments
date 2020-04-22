package com.gmail.killian.news.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.gmail.killian.news.R
import com.gmail.killian.news.adapters.ArticleAdapter
import com.gmail.killian.news.adapters.CategoryAdapter
import com.gmail.killian.news.model.Article
import com.gmail.killian.news.model.Category
import com.gmail.killian.news.model.Source
import com.gmail.killian.news.repositories.ArticleRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ListArticleFragment: Fragment() {
    private val repository =  ArticleRepository()
    private lateinit var recyclerView: RecyclerView

    val category: String by lazy {
        arguments?.getString(ARGS_CATEGORY, "Politique") ?: "Politique"
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.article_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        val titleView = view.findViewById<TextView>(R.id.recyclerTitle)
        titleView.text = category
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        lifecycleScope.launch {
            getData()
        }
    }

    //S'execute dans un thread secondaire
    private suspend fun getData() {
        withContext(Dispatchers.IO) {
            val result = repository.list(category)
            bindData(result)
        }
    }

    //S'execute sur le thread principal
    private suspend fun bindData(result: List<Article>) {
        withContext(Dispatchers.Main) {
            Log.d("Articles", result.toString())

            bindRecycler(result)
        }
    }

    private fun bindRecycler(articles: List<Article>) {
        val adapterRecycler = ArticleAdapter(articles)
        recyclerView.layoutManager = LinearLayoutManager(context)
        recyclerView.adapter = adapterRecycler
    }

    companion object {
        const val ARGS_CATEGORY = "ARGS_CATEGORY"
        fun newInstance(operation: String):ListArticleFragment {
            return ListArticleFragment().apply {
                arguments = bundleOf(ARGS_CATEGORY to operation)
            }
        }
    }


}