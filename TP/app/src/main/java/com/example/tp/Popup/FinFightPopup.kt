package com.example.tp.Popup

import android.app.Dialog
import android.os.Bundle
import android.view.Window
import android.widget.Button
import android.widget.TextView
import com.example.tp.Fragments.FightFragment
import com.example.tp.R

class FinFightPopup(
    private val adapter: FightFragment,
    private val winner: Boolean,
    private val rand: Int
): Dialog(adapter.context)  {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature((Window.FEATURE_NO_TITLE))
        setContentView(R.layout.popup_fin_fight)
        setUpCompoment()
        setUpClose()
    }

    private fun setUpClose() {
        findViewById<Button>(R.id.fermer).setOnClickListener{
            dismiss()
        }
    }

    private fun setUpCompoment() {
        if (winner){
            findViewById<TextView>(R.id.result).text = "VICTOIRE!"
            findViewById<TextView>(R.id.gain).text = "vous aves gagné : " + rand.toString()
        }
        else{
            findViewById<TextView>(R.id.result).text = "DEFAITE..."
        }
    }
}