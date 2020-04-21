package com.gmail.killian.categorylist

import adapters.ArticleAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.Spinner
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.view.children
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.category_list.*
import model.Article

class CategoryList: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var articleAdapter: ArrayAdapter<String>
    private lateinit var spinner: Spinner
    //val planetes

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.category_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        recyclerView = view.findViewById(R.id.recyclerView)
        bindRecyclerView()
    }

    private fun bindSpinner() {

    }

    private fun bindRecyclerView() {
        //créer une liste d'articles
        val articles = listOf<Article>(
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1"),
            Article(title="Article1", description="Description 1", image="image1")
        )
        //créer une instance de l'adapteur
        val adapterRecycler = ArticleAdapter(articles)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager =
            LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}