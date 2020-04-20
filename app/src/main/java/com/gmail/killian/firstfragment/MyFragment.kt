package com.gmail.killian.firstfragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment

class MyFragment: Fragment() {
    var current = 0
    lateinit var counter : TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //val args = arguments ?: bundleOf()
        //arguments = args
        //current = arguments?.getInt("CURRENT") ?: 0
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.first_fragment, container, false).apply {
            counter = findViewById(R.id.textview_first)
        }
        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        changeText()

        view.findViewById<Button>(R.id.button_first).setOnClickListener {
            current ++
            changeText()
            activity?.supportFragmentManager?.beginTransaction()?.apply {
                replace(R.id.container, SecondFragment())

                addToBackStack(null)
            }?.commit()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()

        arguments?.putInt("COUNTER", current)
    }

    private fun changeText() {
        counter.text = "Nombre de clique $current"
    }

}