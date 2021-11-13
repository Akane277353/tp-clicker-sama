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

class CharSearchAdapter(
    private val charList: List<PlayableChar>,
    val context : MainActivity,
    val view: View,
): RecyclerView.Adapter<CharSearchAdapter.ViewHolder>() {

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

        afficheimg(currentChar, holder)
    }

    fun afficheimg(currentChar: PlayableChar, holder: CharSearchAdapter.ViewHolder){
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


}