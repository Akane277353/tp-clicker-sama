package com.example.tp.Model

import java.io.Serializable

class PlayableChar (
    var id: Int,
    var name: String,
    var hp: Int,
    var dam1: Int,
    var dam2: Int,
    var dam3: Int,
    var attack1Level: Int,
    var attack2Level: Int,
    var attack3Level: Int,
    var team: Boolean,
    var buy: Boolean,
    var price: Int
) :Serializable{
    companion object {
        const val CHAR_ID = "id"
        const val CHAR_NAME = "name"
        const val CHAR_HP = "hp"
        const val CHAR_DM1 = "dam1"
        const val CHAR_DM2 = "dam2"
        const val CHAR_DM3 = "dam2"
        const val CHAR_AT1 = "attack1Level"
        const val CHAR_AT2 = "attack2Level"
        const val CHAR_AT3 = "attack3Level"
        const val CHAR_TEAM = "team"
        const val CHAR_BUY = "buy"
        const val CHAR_PRICE = "price"
    }
}