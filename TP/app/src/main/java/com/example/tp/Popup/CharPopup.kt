package com.example.tp.Popup

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.example.tp.Adapter.CharTeamAdapter
import com.example.tp.Fragments.Team
import com.example.tp.Model.PlayableChar
import com.example.tp.Model.Player
import com.example.tp.R

class CharPopup(
    private val adapter: CharTeamAdapter,
    private var currentChar: PlayableChar,
    private val context2: Team
): Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature((Window.FEATURE_NO_TITLE))
        setContentView(R.layout.popup_info_perso)
        setUpCompoment()
        setUpClose()
        button()
    }

    private fun button() {
        findViewById<Button>(R.id.teamButton).setOnClickListener{
            if (currentChar.team){
                adapter.updateChar(currentChar, false)
            }
            else{
                adapter.updateChar(currentChar, true)
            }
        }

        findViewById<Button>(R.id.buttonPv).setOnClickListener{
            if (currentChar.name != "404 Error"){
                if (context2.context.player.gold >= currentChar.hp *100 * context2.context.multi){
                    var char = currentChar
                    currentChar.hp = (currentChar.hp * 2).toInt()
                    context2.updateChar(char, currentChar, char.hp *100 * context2.context.multi)
                    setUpCompoment()
                }
            }
        }

        findViewById<Button>(R.id.buttonAt1).setOnClickListener{
            if (context2.context.player.gold >= currentChar.attack1Level *1000 * context2.context.multi){
                var char = currentChar
                currentChar.attack1Level = currentChar.attack1Level + 1
                currentChar.dam1 = (currentChar.dam1 * 1.2).toInt()
                context2.updateChar(char, currentChar, char.attack1Level *1000 * context2.context.multi)
                setUpCompoment()
            }
        }
        findViewById<Button>(R.id.buttonAt2).setOnClickListener{
            if (context2.context.player.gold >= currentChar.attack2Level *1000 * context2.context.multi){
                var char = currentChar
                currentChar.attack2Level = currentChar.attack2Level + 1
                currentChar.dam2 = (currentChar.dam2 * 1.2).toInt()
                context2.updateChar(char, currentChar, char.attack2Level *1000 * context2.context.multi)
                setUpCompoment()
            }
        }
        findViewById<Button>(R.id.buttonAt3).setOnClickListener{
            if (context2.context.player.gold >= currentChar.attack3Level *1000 * context2.context.multi){
                var char = currentChar
                currentChar.attack3Level = currentChar.attack3Level + 1
                currentChar.dam3 = (currentChar.dam3 * 1.2).toInt()
                context2.updateChar(char, currentChar, char.attack3Level *1000 * context2.context.multi)
                setUpCompoment()
            }
        }
        findViewById<Button>(R.id.buyButton).setOnClickListener{
            if (context2.context.player.gold >= currentChar.price){
                context2.context.updatenbChar()
                var char = currentChar
                currentChar.buy = true
                context2.updateChar(char, currentChar, char.price)
                setUpCompoment()
                dismiss()
            }
        }
    }

    private fun setUpClose() {
        findViewById<ImageView>(R.id.closeButton).setOnClickListener{
            dismiss()
        }
    }

    private fun setUpCompoment() {
        val image = findViewById<ImageView>(R.id.imgItem)

        findViewById<TextView>(R.id.nameChar).text = currentChar.name
        findViewById<TextView>(R.id.pv).text = currentChar.hp.toString()


            findViewById<Button>(R.id.buyButton).setVisibility(View.INVISIBLE)
            findViewById<TextView>(R.id.degat1).text = currentChar.dam1.toString()
            findViewById<TextView>(R.id.degat2).text = currentChar.dam2.toString()
            findViewById<TextView>(R.id.degat3).text = currentChar.dam3.toString()

            if (currentChar.name != "404 Error") {
                findViewById<TextView>(R.id.buttonPv).text = (currentChar.hp * 100).toString()
            }
            else{
                findViewById<TextView>(R.id.buttonPv).setVisibility(View.INVISIBLE)
            }
            findViewById<TextView>(R.id.buttonAt1).text = (currentChar.attack1Level * 1000 * context2.context.multi).toString()
            findViewById<TextView>(R.id.buttonAt2).text = (currentChar.attack2Level * 1000 * context2.context.multi).toString()
            findViewById<TextView>(R.id.buttonAt3).text = (currentChar.attack3Level * 1000 * context2.context.multi).toString()



        if (!currentChar.buy){
            findViewById<Button>(R.id.teamButton).setVisibility(View.INVISIBLE)
            findViewById<Button>(R.id.buttonAt1).setVisibility(View.INVISIBLE)
            findViewById<Button>(R.id.buttonAt2).setVisibility(View.INVISIBLE)
            findViewById<Button>(R.id.buttonAt3).setVisibility(View.INVISIBLE)
            findViewById<TextView>(R.id.buttonPv).setVisibility(View.INVISIBLE)
            findViewById<Button>(R.id.buyButton).setVisibility(View.VISIBLE)
            findViewById<Button>(R.id.buyButton).text = currentChar.price.toString()
        }
        else{
            findViewById<Button>(R.id.buyButton).setVisibility(View.INVISIBLE)
        }
    }

}