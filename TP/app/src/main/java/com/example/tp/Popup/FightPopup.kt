package com.example.tp.Popup

import android.app.Dialog
import android.os.Bundle
import android.view.View
import android.view.Window
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import com.example.tp.Adapter.CharFightAdapter
import com.example.tp.Model.PlayableChar
import com.example.tp.R

class FightPopup(
    private val adapter: CharFightAdapter,
    private var currentChar: PlayableChar,
    private val ennemi: List<PlayableChar>,
    var holder: CharFightAdapter.ViewHolder,
): Dialog(adapter.context) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature((Window.FEATURE_NO_TITLE))
        setContentView(R.layout.popup_fight)
        setUpCompoment()
        setUpClose()
        button()
    }

    private fun button() {
        findViewById<Button>(R.id.at1).setOnClickListener(){
            adapter.context2.updateAt(currentChar, currentChar.dam1)
            setUpCompoment()
        }
        findViewById<Button>(R.id.at2).setOnClickListener(){
            adapter.context2.updateAt(currentChar, currentChar.dam2)
            setUpCompoment()
        }
        findViewById<Button>(R.id.at3).setOnClickListener(){
            adapter.context2.updateAt(currentChar, currentChar.dam3)
            setUpCompoment()
        }
        findViewById<Button>(R.id.ennemi1).setOnClickListener(){
            adapter.context2.updateEn(currentChar, 1)
            setUpCompoment()
        }
        findViewById<Button>(R.id.ennemi2).setOnClickListener(){
            adapter.context2.updateEn(currentChar, 2)
            setUpCompoment()
        }
        findViewById<Button>(R.id.ennemi3).setOnClickListener(){
            adapter.context2.updateEn(currentChar, 3)
            setUpCompoment()
        }
        findViewById<Button>(R.id.valider).setOnClickListener(){
            var valide = true
            if ( currentChar.hp > 0){
                if (adapter.context2.nbAttaque(currentChar) == 0){
                    Toast.makeText(adapter.context, "vous n'avez pas sélectionné d'attaque", Toast.LENGTH_SHORT).show()
                    valide = false
                }
                if (adapter.context2.ennemiName(currentChar) == ""){
                    Toast.makeText(adapter.context, "vous n'avez pas sélectionné de cible", Toast.LENGTH_SHORT).show()
                    valide = false
                }
            }
            if (valide) {
                adapter.modifAt(holder, currentChar)
                adapter.modifEn(holder, currentChar)
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

        findViewById<TextView>(R.id.value).text = adapter.context2.nbAttaque(currentChar).toString()
        findViewById<TextView>(R.id.at1).text = currentChar.dam1.toString()
        findViewById<TextView>(R.id.at2).text = currentChar.dam2.toString()
        findViewById<TextView>(R.id.at3).text = currentChar.dam3.toString()

        if (ennemi[0].hp > 0){
            findViewById<TextView>(R.id.ennemi1).text = ennemi[0].name
        }
        else{
            findViewById<TextView>(R.id.ennemi1).setVisibility(View.INVISIBLE)
        }

        if (ennemi.size >= 2){
            if (ennemi[1].hp > 0){
                findViewById<TextView>(R.id.ennemi2).text = ennemi[1].name
            }
            else{
                findViewById<TextView>(R.id.ennemi2).setVisibility(View.INVISIBLE)
            }
        }
        else{
            findViewById<TextView>(R.id.ennemi2).setVisibility(View.INVISIBLE)
        }

        if (ennemi.size == 3 ){
            if (ennemi[2].hp > 0){
                findViewById<TextView>(R.id.ennemi3).text = ennemi[2].name
            }
            else{
                findViewById<TextView>(R.id.ennemi3).setVisibility(View.INVISIBLE)
            }
        }
        else{
            findViewById<TextView>(R.id.ennemi3).setVisibility(View.INVISIBLE)
        }
    }
}