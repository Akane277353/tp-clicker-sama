package com.example.tp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.Adapter.CharFightAdapter
import com.example.tp.MainActivity
import com.example.tp.Model.Fight
import com.example.tp.Model.PlayableChar
import com.example.tp.R

class FightFragment( private val context: MainActivity, val charList: List<PlayableChar>, val ennemi: List<PlayableChar>) : Fragment() {

    var listAttaque = arrayListOf<Fight>()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        for (i in charList){
            listAttaque.add(
                Fight(i.name, 0, "")
            )
        }

        val view = inflater.inflate(R.layout.activity_combat, container, false)
        refresh(view)
        return view
    }

    private fun refresh(view: View) {
        val charRecyclerView = view?.findViewById<RecyclerView>(R.id.charRecyclerView)
        val charRecyclerViewBottom = view?.findViewById<RecyclerView>(R.id.charRecyclerViewBottom)
        charRecyclerView?.adapter = CharFightAdapter(ennemi, charList, context, this, view, true)
        charRecyclerViewBottom?.adapter = CharFightAdapter(charList, ennemi, context, this, view, false)
    }

    fun updateAt(char: PlayableChar, nb: Int){
        for (i in listAttaque){
            if(i.name == char.name){
                i.at = nb
            }
        }
    }

    fun updateEn(char: PlayableChar, nb: Int){
        for (i in listAttaque){
            if(i.name == char.name){
                i.cible = ennemi[nb-1].name
            }
        }
    }

    fun nbAttaque(char: PlayableChar): Int{
        for (i in listAttaque){
            if(i.name == char.name){
                return i.at
            }
        }
        return 0
    }

    fun ennemiName(char: PlayableChar): String{
        for (i in listAttaque){
            if(i.name == char.name){
                return i.cible
            }
        }
        return ""
    }

}