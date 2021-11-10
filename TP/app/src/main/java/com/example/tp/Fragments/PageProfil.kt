package com.example.tp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.tp.Model.Player
import com.example.tp.R

class PageProfil( context: Context, player: Player) : Fragment() {

    var player = player

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.activity_page_profil, container, false)

        view.findViewById<TextView>(R.id.nombreClique).text = player.nbClique.toString()//Récupère et affiche nombre de clique joueur
        view.findViewById<TextView>(R.id.goldTextProfil).text = player.gold.toString()//Récupère et affiche NBgold joueur
        view.findViewById<TextView>(R.id.pseudoPlayer).text = player.pseudo//Récupère et affiche pseudo joueur
        view.findViewById<TextView>(R.id.niveauPlayer).text = player.xp.toString()//Récupère et affiche niveau joueur
        view.findViewById<TextView>(R.id.nombrePerso).text = player.nbPerso.toString()//Récupère et affiche niveau joueur

        return view
    }
}