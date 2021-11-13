package com.example.tp.Model

import java.io.Serializable


open class Player (
    var id: Int,
    var pseudo: String,
    var gold: Int,
    var xp: Int,
    var nbClique: Int,
    var nbPerso: Int,
    var multiplicateur: Int,
    var detecteurPas: Boolean
): Serializable {
    companion object {
        const val PLAYER_ID = "id"
        const val PLAYER_PSEUDO = "pseudo"
        const val PLAYER_GOLD = "gold"
        const val PLAYER_XP = "xp"
        const val PLAYER_NBCLIQUE = "nbClique"
        const val PLAYER_NBPERSO = "nbPerso"
        const val PLAYER_MULTIPLICATEUR = "multiplicateur"
        const val DETECTEUR_PAS = "detecteurPas"
    }
}