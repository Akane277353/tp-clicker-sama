package com.example.tp.Adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.example.tp.Fragments.Team
import com.example.tp.MainActivity
import com.example.tp.Popup.CharPopup
import com.example.tp.Model.PlayableChar
import com.example.tp.Model.Player
import com.example.tp.R

class CharTeamAdapter(
    private val charList: List<PlayableChar>,
    val context2: Team,
    val context : MainActivity,
    val view: View,
    private val pos: Boolean
): RecyclerView.Adapter<CharTeamAdapter.ViewHolder>() {

    class ViewHolder(view: View): RecyclerView.ViewHolder(view){
        val charImage = view.findViewById<ImageView>(R.id.charTeam)
    }

    fun defView(parent: ViewGroup): View{
        return LayoutInflater.from(parent.context).inflate(R.layout.character_team, parent, false)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(defView(parent))
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentChar = charList[position]

            if (pos){
                if (currentChar.team == true){
                    if (currentChar.name == "jsp") {
                        holder.charImage.setImageResource(R.mipmap.sombra)
                    }
                    else if (currentChar.name == "dude") {
                        holder.charImage.setImageResource(R.mipmap.ashe)
                    }
                    else if (currentChar.name == "bob") {
                        holder.charImage.setImageResource(R.mipmap.heights)
                    }
                    holder.itemView.setOnClickListener{
                        CharPopup(this, currentChar, context2).show()
                    }
                }
            }
        else{
                if (currentChar.name == "jsp") {
                    holder.charImage.setImageResource(R.mipmap.sombra)
                }
                else if (currentChar.name == "dude") {
                    holder.charImage.setImageResource(R.mipmap.ashe)
                }
                else if (currentChar.name == "bob") {
                    holder.charImage.setImageResource(R.mipmap.heights)
                }

                holder.itemView.setOnClickListener{
                    CharPopup(this, currentChar, context2).show()
                }
            }
    }

    override fun getItemCount(): Int = charList.size

    fun updateChar(char: PlayableChar, bool: Boolean){
        val current = char
        char.team = bool
        context2.updateTeam(current, char, view)
    }
    fun inTeamNb(): Int {
        return context2.inTeam().size
    }
}