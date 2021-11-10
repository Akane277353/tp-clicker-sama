package com.example.tp

import android.view.View
import android.widget.Button
import android.widget.LinearLayout
import com.example.tp.Fragments.*
import com.example.tp.Model.PlayableChar
import com.example.tp.Model.Player

class UpdateFragment(private val context: MainActivity, val player: Player, val charList: List<PlayableChar>) {

    lateinit var currentFragment: String

    val view = R.layout.activity_main

    fun switch(left: Boolean){
        if (currentFragment == "home" && !left){
            goTeam(false)
        }
        else if (currentFragment == "home" && left){
            goFight(false)
        }
        else if (currentFragment == "team" && !left){
            goShop(false)
        }
        else if (currentFragment == "team" && left){
            goClicker(false)
        }
        else if (currentFragment == "searchfight" && left){
            goShop(false)
        }
        else if (currentFragment == "searchfight" && !left){
            goClicker(false)
        }
        else if (currentFragment == "shop" && left){
            goTeam(false)
        }
        else if (currentFragment == "shop" && !left){
            goFight(false)
        }
    }

    fun updateCurFrag(str: String){
        currentFragment = str
    }

    fun goProfil(){
        context.findViewById<Button>(R.id.profilButton).setOnClickListener {
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.INVISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, PageProfil( context, player))
            trans.addToBackStack(null)
            trans.commit()
            currentFragment = "profil"
        }
    }

    fun goTeam(bool: Boolean) {
        if (bool){
            context.findViewById<Button>(R.id.teamButton).setOnClickListener {
                context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
                context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
                val trans = context.supportFragmentManager.beginTransaction()
                trans.replace(R.id.fragmentContainer, Team(context, player, charList))
                trans.addToBackStack(null)
                trans.commit()
                currentFragment = "team"
            }
        }
        else{
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, Team(context, player, charList))
            trans.addToBackStack(null)
            trans.commit()
            currentFragment = "team"
        }
    }
    fun goClicker(bool: Boolean){
        if (bool){
            context.findViewById<Button>(R.id.clickButton).setOnClickListener {
                context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
                context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
                val trans = context.supportFragmentManager.beginTransaction()
                trans.replace(R.id.fragmentContainer, HomeFragment(context))
                trans.addToBackStack(null)
                trans.commit()
                currentFragment = "home"
            }
        }
        else{
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, HomeFragment(context))
            trans.addToBackStack(null)
            trans.commit()
            currentFragment = "home"
        }
    }

    fun goShop(bool: Boolean){
        if (bool){
            context.findViewById<Button>(R.id.shopButton).setOnClickListener {
                context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
                context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
                val trans = context.supportFragmentManager.beginTransaction()
                trans.replace(R.id.fragmentContainer, Shop( context))
                trans.addToBackStack(null)
                trans.commit()
                currentFragment = "shop"
            }
        }
        else{
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, Shop(context))
            trans.addToBackStack(null)
            trans.commit()
            currentFragment = "shop"
        }
    }

    fun goFight(bool: Boolean){
        if (bool){
            context.findViewById<Button>(R.id.fightButton).setOnClickListener {
                context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
                context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
                val trans = context.supportFragmentManager.beginTransaction()
                trans.replace(R.id.fragmentContainer, searchFightFragment(context, charList))
                trans.addToBackStack(null)
                trans.commit()
                currentFragment = "searchfight"
            }
        }
        else{
            context.findViewById<LinearLayout>(R.id.layoutTop).setVisibility(View.VISIBLE)
            context.findViewById<LinearLayout>(R.id.layoutBot).setVisibility(View.VISIBLE)
            val trans = context.supportFragmentManager.beginTransaction()
            trans.replace(R.id.fragmentContainer, searchFightFragment(context, charList))
            trans.addToBackStack(null)
            trans.commit()
            currentFragment = "searchfight"
        }
    }
}