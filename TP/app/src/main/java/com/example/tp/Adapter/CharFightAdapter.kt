package com.example.tp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.Fragments.FightFragment
import com.example.tp.MainActivity
import com.example.tp.Model.PlayableChar
import com.example.tp.Popup.FightPopup
import com.example.tp.R

class CharFightAdapter(
    private val charList: List<PlayableChar>,
    private val ennemi: List<PlayableChar>,
    val context : MainActivity,
    val context2 : FightFragment,
    val view: View,
    private val pos: Boolean
): RecyclerView.Adapter<CharFightAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val charImage = view.findViewById<ImageView>(R.id.charTeam)
        val charText = view.findViewById<TextView>(R.id.pvPerso)
        val textAt = view.findViewById<TextView>(R.id.attaque)
        val textEn = view.findViewById<TextView>(R.id.ennemi)
        val textNo = view.findViewById<TextView>(R.id.nom)
    }

    private fun defView(parent: ViewGroup): View{
        return LayoutInflater.from(parent.context).inflate(R.layout.character_fight, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(defView(parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentChar = charList[position]

        afficheimg(currentChar, holder)

        holder.charText.text = currentChar.hp.toString()
        if (pos){
            holder.textNo.text = currentChar.name
            holder.textAt.setVisibility(View.INVISIBLE)
            holder.textEn.setVisibility(View.INVISIBLE)

        }
        else{
            holder.textNo.setVisibility(View.INVISIBLE)
            holder.textEn.text = context2.ennemiName(currentChar)

            if(currentChar.hp > 0){
                if (context2.nbAttaque(currentChar) == 0){
                    holder.textAt.setVisibility(View.INVISIBLE)
                }
                else{
                    holder.textAt.setVisibility(View.VISIBLE)
                    holder.textAt.text = context2.nbAttaque(currentChar).toString()
                }
                holder.textEn.text = context2.ennemiName(currentChar)

                holder.itemView.setOnClickListener{
                    FightPopup(this, currentChar, ennemi, holder).show()
                }
            }
            else{
                holder.textAt.setVisibility(View.INVISIBLE)
                holder.textEn.text = ""
            }
        }
    }

    fun afficheimg(currentChar: PlayableChar, holder: ViewHolder){
        if (currentChar.name == "Giusepe") {
            holder.charImage.setImageResource(R.mipmap.giusepe)
        }
        else if (currentChar.name == "Rigobert") {
            holder.charImage.setImageResource(R.mipmap.rigobert)
        }
        else if (currentChar.name == "George") {
            holder.charImage.setImageResource(R.mipmap.george)
        }
        else if (currentChar.name == "Semi Chips") {
            holder.charImage.setImageResource(R.mipmap.semi_chips)
        }
        else if (currentChar.name == "404 Error") {
            holder.charImage.setImageResource(R.mipmap.error)
        }
    }

    override fun getItemCount(): Int = charList.size

    fun modifAt(holder: ViewHolder, char: PlayableChar){
        holder.textAt.setVisibility(View.VISIBLE)
        holder.textAt.text = context2.nbAttaque(char).toString()
    }

    fun modifEn(holder: ViewHolder, char: PlayableChar){
        holder.textEn.setVisibility(View.VISIBLE)
        holder.textEn.text = context2.ennemiName(char)
    }

}