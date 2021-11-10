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
        val charRecyclerView = view?.findViewById<RecyclerView>(R.id.charRecyclerView)
        charRecyclerView?.adapter = CharSearchAdapter(inTeam(), context, view)

        view.findViewById<Button>(R.id.search_button).setOnClickListener {
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.INVISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.INVISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, FightFragment(context, inTeam(), inTeam()))
            trans.addToBackStack(null)
            trans.commit()
        }
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