package com.example.tp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.Adapter.CharFightAdapter
import com.example.tp.Adapter.CharSearchAdapter
import com.example.tp.MainActivity
import com.example.tp.Model.PlayableChar
import com.example.tp.R
import com.example.tp.UpdateFragment
import kotlin.random.Random
import kotlin.random.Random.Default.nextInt

class searchFightFragment( private val context: MainActivity, val charList: List<PlayableChar>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.search_fight, container, false)
        refresh(view)
        return view
    }

    private fun refresh(view: View) {
        var enList = arrayListOf<PlayableChar>()
        enList = initList(enList)
        val charRecyclerView = view?.findViewById<RecyclerView>(R.id.charRecyclerView)
        charRecyclerView?.adapter = CharSearchAdapter(inTeam(), context, view)
        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            context.swipe = false
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.INVISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.INVISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, FightFragment(context, inTeam(), enList))
            trans.addToBackStack(null)
            trans.commit()
        }
    }

    private fun initList(enList: ArrayList<PlayableChar>): ArrayList<PlayableChar> {
        for (i in 0..2){
            val char = PlayableChar(
                i,
                randomName(),
                nextInt(context.player.xp * 10, context.player.xp *100),
                nextInt(context.player.xp * 10, context.player.xp *100),
                nextInt(context.player.xp * 10, context.player.xp *100),
                nextInt(context.player.xp * 10, context.player.xp *100),
                0,
                0,
                0,
                true,
                true,
                0
            )
            enList.add(char)
        }
        return enList
    }

    private fun randomName(): String {
        val listNom = getListNom()
        val nb = nextInt(0, listNom.size)
        return listNom[nb]
    }

    fun getListNom(): ArrayList<String>{
        var list = arrayListOf<String>()
        list.add("Rodriguez")
        list.add("Natacha")
        list.add("Gontran")
        list.add("Juste")
        list.add("Jean")
        list.add("Ananie")
        list.add("Martine")
        list.add("Sophie")
        list.add("Tatiana")
        list.add("Basile")
        list.add("Charlotte")
        list.add("Guillaume")
        list.add("Judith")
        list.add("Marc")
        list.add("Nestor")
        list.add("Paul")
        list.add("Paulin")
        list.add("Richard")
        list.add("Sabrina")
        list.add("Muchacho")
        list.add("Peperoni")
        return list
    }

    fun inTeam(): List<PlayableChar> {
        val newList = arrayListOf<PlayableChar>()
        for (item in charList){
            if (item.team == true){
                newList.add(item)
            }
        }
        return newList
    }
}