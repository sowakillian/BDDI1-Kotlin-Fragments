package com.gmail.killian.news.adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule
import com.gmail.killian.news.R
import com.gmail.killian.news.model.Article
import com.gmail.killian.news.model.Category

@GlideModule

class ArticleAdapter(private val dataset: List<Article>) :
    RecyclerView.Adapter<ArticleAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Article) {
            val txtTitle = root.findViewById<TextView>(R.id.article_title)
            val txtDesc = root.findViewById<TextView>(R.id.article_description)
            val articleImage = root.findViewById<ImageView>(R.id.article_image)
            txtTitle.text = "${item.title.take(60)}"
            txtDesc.text = item.description

            Log.d("itemUrl", item.url)
            Glide.with(root)  //2
                .load(item.urlToImage)
                .placeholder(R.drawable.coming_article_photo)
                .centerCrop()
                .into(articleImage)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.article_list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(dataset[position])
    }


    override fun getItemCount(): Int = dataset.size
}
