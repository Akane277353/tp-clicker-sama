package com.example.tp.Stockage

import android.content.Context
import com.example.tp.Model.PlayableChar
import org.json.JSONObject
class CharJSONFileStorage private constructor(context: Context):
    JSONFileStorage<PlayableChar>(context, NAME) {
    companion object {
        private const val NAME = "char"

        private var STORAGE: CharJSONFileStorage? = null

        fun get(context: Context): CharJSONFileStorage {
            if (STORAGE == null) {
                STORAGE = CharJSONFileStorage(context)
            }
            return STORAGE as CharJSONFileStorage
        }
    }

    override fun create(id: Int, obj: PlayableChar): PlayableChar {
        return PlayableChar(
            id,
            obj.name,
            obj.hp,
            obj.dam1,
            obj.dam2,
            obj.dam3,
            obj.attack1Level,
            obj.attack2Level,
            obj.attack3Level,
            obj.team,
            obj.buy,
            obj.price
        )
    }

    override fun objectToJson(id: Int, obj: PlayableChar): JSONObject {
        val jsonObject = JSONObject()
        jsonObject.put(PlayableChar.CHAR_ID, obj.id)
        jsonObject.put(PlayableChar.CHAR_NAME, obj.name)
        jsonObject.put(PlayableChar.CHAR_HP, obj.hp)
        jsonObject.put(PlayableChar.CHAR_DM1, obj.dam1)
        jsonObject.put(PlayableChar.CHAR_DM2, obj.dam2)
        jsonObject.put(PlayableChar.CHAR_DM3, obj.dam3)
        jsonObject.put(PlayableChar.CHAR_AT1, obj.attack1Level)
        jsonObject.put(PlayableChar.CHAR_AT2, obj.attack2Level)
        jsonObject.put(PlayableChar.CHAR_AT3, obj.attack3Level)
        jsonObject.put(PlayableChar.CHAR_TEAM, obj.team)
        jsonObject.put(PlayableChar.CHAR_BUY, obj.buy)
        jsonObject.put(PlayableChar.CHAR_PRICE, obj.price)
        return jsonObject
    }

    override fun jsonToObject(json: JSONObject): PlayableChar {
        return PlayableChar(
            json.get(PlayableChar.CHAR_ID) as Int,
            json.get(PlayableChar.CHAR_NAME) as String,
            json.get(PlayableChar.CHAR_HP) as Int,
            json.get(PlayableChar.CHAR_DM1) as Int,
            json.get(PlayableChar.CHAR_DM2) as Int,
            json.get(PlayableChar.CHAR_DM3) as Int,
            json.get(PlayableChar.CHAR_AT1) as Int,
            json.get(PlayableChar.CHAR_AT2) as Int,
            json.get(PlayableChar.CHAR_AT3) as Int,
            json.get(PlayableChar.CHAR_TEAM) as Boolean,
            json.get(PlayableChar.CHAR_BUY) as Boolean,
            json.get(PlayableChar.CHAR_PRICE) as Int
        )
    }
}