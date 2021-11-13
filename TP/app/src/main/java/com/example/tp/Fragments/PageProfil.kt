package com.example.tp.Fragments

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.core.view.isInvisible
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import com.example.tp.MainActivity
import com.example.tp.MainActivity.Companion.pays

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

        view.findViewById<TextView>(R.id.paysPlayer).text = pays



        if (player.detecteurPas == true){
            view.findViewById<Button>(R.id.activite_button).text = "Ne plus gagner des golds en marchant"
        }
        else{
            view.findViewById<Button>(R.id.activite_button).text = "Gagner des golds en marchant"
        }

        view.findViewById<Button>(R.id.activite_button).setOnClickListener {
            if (player.detecteurPas == false){
                (activity as MainActivity?)?.checkForPermissions(android.Manifest.permission.ACTIVITY_RECOGNITION, "activity", 1)
                view.findViewById<Button>(R.id.activite_button).text = "Ne plus gagner des golds en marchant"
                player.detecteurPas = true
                (activity as MainActivity?)?.registerSensor()
            }
            else{
                view.findViewById<Button>(R.id.activite_button).text = "Gagner des golds en marchant"
                player.detecteurPas = false
                (activity as MainActivity?)?.unregisterSensor()
            }
            (activity as MainActivity?)?.update()

        }

        return view
    }
}