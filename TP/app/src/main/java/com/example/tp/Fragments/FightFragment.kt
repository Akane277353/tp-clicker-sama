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
import com.example.tp.MainActivity
import com.example.tp.Model.Fight
import com.example.tp.Model.PlayableChar
import com.example.tp.Popup.FinFightPopup
import com.example.tp.R

class FightFragment( val context: MainActivity, private val charList: List<PlayableChar>, private val ennemi: List<PlayableChar>) : Fragment() {

    var listAttaque = arrayListOf<Fight>()

    var winner= false

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
        button(view)
        return view
    }

    private fun refresh(view: View) {
        val charRecyclerView = view?.findViewById<RecyclerView>(R.id.charRecyclerView)
        val charRecyclerViewBottom = view?.findViewById<RecyclerView>(R.id.charRecyclerViewBottom)
        charRecyclerView?.adapter = CharFightAdapter(ennemi, charList, context, this, view, true)
        charRecyclerViewBottom?.adapter = CharFightAdapter(charList, ennemi, context, this, view, false)
    }

    fun button(view: View){
        view.findViewById<Button>(R.id.terminer).setOnClickListener {
            charList[0].hp = 0
            for (plr in listAttaque){
                if (envie(plr)){
                    attaque(plr)
                }
            }
            if(end()){
                resetPv()
                back()
            }
            refresh(view)
        }
    }

    fun envie(plr: Fight): Boolean{
        for (el in charList){
            if (el.name == plr.name){
                if(el.hp > 0){
                    return true
                }
            }
        }
        return false
    }

    fun attaque(plr: Fight){
        for (i in ennemi){
            if (i.name == plr.cible){
                if ( i.hp > 0){
                    i.hp = i.hp - plr.at
                    if (i.hp < 0){
                        i.hp = 0
                    }
                }
                else{
                    i.hp = 0
                }
            }
        }
    }

    fun resetPv(){
        for (el in charList){
            el.hp = 1
        }
    }

    fun back(){
        FinFightPopup(this, winner).show()
        context.swipe = true
        context.update()
        context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
        context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
        val trans = context.supportFragmentManager.beginTransaction()
        trans.replace(R.id.fragmentContainer, searchFightFragment(context, charList))
        trans.addToBackStack(null)
        trans.commit()
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

    fun end():Boolean{
        var pven = 0
        var pvplr = 0
        for (en in ennemi){
            pven = pven + en.hp
        }
        for (plr in charList){
            pvplr = pvplr + plr.hp
        }
        if (pven <= 0){
            winner = true
        }
        else{
            winner = false
        }
        return pven <= 0 || pvplr <= 0
    }

}