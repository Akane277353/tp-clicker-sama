package com.example.tp.Fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.Adapter.CharTeamAdapter
import com.example.tp.MainActivity
import com.example.tp.Model.PlayableChar
import com.example.tp.Model.Player
import com.example.tp.R

class Team(val context: MainActivity, var player: Player, var charList: List<PlayableChar>) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        var view = inflater?.inflate(R.layout.team, container, false)
        refresh(view)
        return view
    }

    private fun refresh(view: View) {
        val charRecyclerView = view?.findViewById<RecyclerView>(R.id.charRecyclerView)
        val charRecyclerViewBottom = view?.findViewById<RecyclerView>(R.id.charRecyclerViewBottom)
        charRecyclerView?.adapter = CharTeamAdapter(inTeam(),  this, context, view, player, true)
        charRecyclerViewBottom?.adapter = CharTeamAdapter(outTeam(), this, context, view, player, false)
    }

    fun itemPose(char: PlayableChar): Int{
        var nb = 0
        for (item in charList){
            if (item == char){
                return nb
            }
            nb = nb + 1
        }
        return -1
    }

    fun updateChar(char: PlayableChar, newVal: PlayableChar, price: Int){
        val pos = itemPose(char)
        (activity as MainActivity).updateChar(newVal, pos)
        (activity as MainActivity).achat(price)
    }

    fun updateTeam(char: PlayableChar, newVal: PlayableChar, view: View){
        val pos = itemPose(char)
        (activity as MainActivity).updateChar(newVal, pos)
        refresh(view)
    }

    fun outTeam(): List<PlayableChar> {
        val newList = arrayListOf<PlayableChar>()
        for (item in charList){
            if (item.team == false){
                newList.add(item)
            }
        }
        return newList
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