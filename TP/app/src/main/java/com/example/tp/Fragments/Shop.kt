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

class Shop( var context: MainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.activity_shop, container, false)

        //Quand le bouton est cliqué, appel la fonction pour doubler l'or gagné
        view.findViewById<Button>(R.id.doubleOr).text ="Double l'or par clique    Durée : 10 min Coût : "+  (300 * context.multi/10).toString() +" Gold"
        view.findViewById<Button>(R.id.doubleOr).setOnClickListener {
            context.doubleOr()
        }

        //Quand le bouton est cliqué, appel la fonction pour triplé l'or gagné
        view.findViewById<Button>(R.id.tripleOr).text ="Triple l'or par clique    Durée : 5 min Coût : "+  (1000 * context.multi/10).toString() +" Gold"
        view.findViewById<Button>(R.id.tripleOr).setOnClickListener {
            context.tripleOr()
        }

        //Quand le bouton est cliqué, appel la fonction pour cliquer automatiquement
        view.findViewById<Button>(R.id.cliqueAuto).text ="Clique automatique Durée : 30 min Coût : "+  (5000 * context.multi/10).toString() +" Gold"
        view.findViewById<Button>(R.id.cliqueAuto).setOnClickListener {
            context.cliqueAuto()
        }

        return view
    }
}