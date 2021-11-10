package com.example.tp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import com.example.tp.MainActivity
import com.example.tp.R

class Shop( context: Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_shop, container, false)

        view.findViewById<Button>(R.id.doubleOr).setOnClickListener {
            (activity as MainActivity?)?.doubleOr()
        }

        view.findViewById<Button>(R.id.tripleOr).setOnClickListener {
            (activity as MainActivity?)?.tripleOr()
        }

        view.findViewById<Button>(R.id.cliqueAuto).setOnClickListener {
            (activity as MainActivity?)?.cliqueAuto()
        }

        return view
    }
}