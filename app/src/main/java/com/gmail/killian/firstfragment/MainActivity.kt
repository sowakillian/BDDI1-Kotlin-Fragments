package com.gmail.killian.firstfragment

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //créer une instance du fragment
        val fragment = MyFragment()

        // créer une transaction sur le fragment manager
        supportFragmentManager.beginTransaction().apply {
            //replacer le précédent fragment, s'il existe
            replace(R.id.container, fragment)

            //ajouter la transaction dans la stack
            addToBackStack(null)
        }.commit()
        //valider la transaction
    }
}
