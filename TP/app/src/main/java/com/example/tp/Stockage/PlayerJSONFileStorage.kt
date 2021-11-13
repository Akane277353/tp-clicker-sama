package com.example.tp.Stockage

import android.content.Context
import com.example.tp.Model.Player
import com.example.tp.Model.Player.Companion.DETECTEUR_PAS
import com.example.tp.Model.Player.Companion.PLAYER_GOLD
import com.example.tp.Model.Player.Companion.PLAYER_ID
import com.example.tp.Model.Player.Companion.PLAYER_MULTIPLICATEUR
import com.example.tp.Model.Player.Companion.PLAYER_NBCLIQUE
import com.example.tp.Model.Player.Companion.PLAYER_NBPERSO
import com.example.tp.Model.Player.Companion.PLAYER_PSEUDO
import com.example.tp.Model.Player.Companion.PLAYER_XP
import org.json.JSONObject
class PlayerJSONFileStorage  private constructor(context: Context):
    JSONFileStorage<Player>(context, NAME){
    companion object{
        private const val NAME = "player"

        private var STORAGE: PlayerJSONFileStorage? = null

        fun get(context: Context): PlayerJSONFileStorage {
            if(STORAGE == null){
                STORAGE = PlayerJSONFileStorage(context)
            }
            return STORAGE as PlayerJSONFileStorage
        }
    }
    override fun create(id: Int, obj: Player): Player{
        return Player(obj.id, obj.pseudo, obj.gold, obj.xp, obj.nbClique, obj.nbPerso, obj.multiplicateur, obj.detecteurPas)
    }

    override fun objectToJson(id: Int, obj: Player): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(PLAYER_ID, id)
        jsonObject.put(PLAYER_PSEUDO, obj.pseudo)
        jsonObject.put(PLAYER_GOLD, obj.gold)
        jsonObject.put(PLAYER_XP, obj.xp)
        jsonObject.put(PLAYER_NBCLIQUE, obj.nbClique)
        jsonObject.put(PLAYER_NBPERSO, obj.nbPerso)
        jsonObject.put(PLAYER_MULTIPLICATEUR, obj.multiplicateur)
        jsonObject.put(DETECTEUR_PAS, obj.detecteurPas)
        return jsonObject
    }

    override fun jsonToObject(json: JSONObject): Player {
        return Player(
            json.get(PLAYER_ID) as Int,
            json.get(PLAYER_PSEUDO) as String,
            json.get(PLAYER_GOLD) as Int,
            json.get(PLAYER_XP) as Int,
            json.get(PLAYER_NBCLIQUE) as Int,
            json.get(PLAYER_NBPERSO) as Int,
            json.get(PLAYER_MULTIPLICATEUR) as Int,
            json.get(DETECTEUR_PAS) as Boolean
        )
    }
}