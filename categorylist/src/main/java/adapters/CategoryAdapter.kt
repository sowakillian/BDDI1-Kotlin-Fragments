package adapters

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.gmail.killian.categorylist.R
import com.bumptech.glide.Glide
import com.bumptech.glide.module.AppGlideModule;
import com.bumptech.glide.annotation.GlideModule
import model.Category
@GlideModule

class CategoryAdapter(private val dataset: List<Category>) :
    RecyclerView.Adapter<CategoryAdapter.ViewHolder>() {
    class ViewHolder(val root: View) : RecyclerView.ViewHolder(root) {
        fun bind(item: Category) {
            val txtTitle = root.findViewById<TextView>(R.id.category_name)
            val txtDesc = root.findViewById<TextView>(R.id.category_description)
            val viewImage = root.findViewById<ImageView>(R.id.category_image)
            txtTitle.text = item.name
            txtDesc.text = item.description

            Log.d("itemUrl", item.url)
            Glide.with(root)  //2
                .load(item.url)
                .centerCrop()
                .into(viewImage)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val rootView = LayoutInflater.from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return ViewHolder(rootView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) = holder.bind(dataset[position])


    override fun getItemCount(): Int = dataset.size
}