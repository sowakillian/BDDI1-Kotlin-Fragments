package com.gmail.killian.categorylist

import adapters.CategoryAdapter
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import model.Category

class CategoryList: Fragment() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var categoryAdapter: ArrayAdapter<String>

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
        val categories = listOf<Category>(
            Category(name="Politique", description="Gestion du coronavirus, tensions après les municipales, comment Macron gère tout cette crise actuelle en France", url="https://images.sudouest.fr/2019/01/31/5c53448666a4bd53506a492b/widescreen/1000x500/emmanuel-macron-dans.jpg?v1"),
            Category(name="Economie", description="Le premier ministre l'a annoncé, la pire récession depuis 1945 aura lieu après cette crise du coronavirus.. aie !", url="https://www.actu-economie.com/wp-content/uploads/2018/10/L-economie-francaise-repart-bel-et-bien-mais-min-min.jpg"),
            Category(name="Education", description="Reprise de l'école le 11 mai, comment l'état va gérer la distanciation pour les plus petits, affaire à suivre !", url="https://3er1viui9wo30pkxh1v2nh4w-wpengine.netdna-ssl.com/wp-content/uploads/sites/113/2016/11/Minecraft-3-1024x683.jpg"),
            Category(name="Pandémie", description="Description 1", url="https://m1.quebecormedia.com/emp/emp/asdasdasdasddasdasdf8283d01-5947-42a8-977c-6b0cf2a66a4c_ORIGINAL.jpg?impolicy=crop-resize&x=0&y=0&w=0&h=0&width=650&height=650"),
            Category(name="Sciences", description="Description 1", url="https://img.huffingtonpost.com/asset/5c92d938220000c7001b4e98.jpeg?ops=scalefit_630_noupscale"),
            Category(name="Ecologie", description="Description 1", url="https://static.actu.fr/uploads/2019/05/AdobeStock_112721620-854x567.jpeg")
        )
        //créer une instance de l'adapteur
        val adapterRecycler = CategoryAdapter(categories)
        //définir l'orientation des élements (vertical)
        recyclerView.layoutManager =
            LinearLayoutManager(context)
        //associer l'adapter à la recyclerview
        recyclerView.adapter = adapterRecycler
    }
}