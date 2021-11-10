package com.example.tp.Fragments

import android.content.Context

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.example.tp.MainActivity
import com.example.tp.R


class HomeFragment( context: Context) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_home, container, false)

        view.findViewById<Button>(R.id.clickButton).setOnClickListener {
            (activity as MainActivity?)?.updateGold()
            (activity as MainActivity?)?.updateNbClique()
        }

        return view
    }

}