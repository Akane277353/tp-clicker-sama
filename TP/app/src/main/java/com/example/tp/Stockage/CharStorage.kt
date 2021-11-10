package com.example.tp.Stockage

import android.content.Context
import android.content.SharedPreferences
import com.example.tp.Model.PlayableChar

object CharStorage {

    private const val PREF_NAME = "tp.info507.clickersama.preferences"
    private const val PREF_STORAGE = "storage"
    private const val PREF_STORAGE_FILE_JSON = 1
    private const val PREF_STORAGE_DEFAULT = PREF_STORAGE_FILE_JSON


    private fun getPreferences(context: Context): SharedPreferences {
        return context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
    }

    fun getPreferencesStorage(context: Context): Int {
        return getPreferences(context).getInt(PREF_STORAGE, PREF_STORAGE_DEFAULT)
    }

    fun setPreferencesStorage(context: Context, prefStorage: Int) {
        getPreferences(context).edit().putInt(PREF_STORAGE, prefStorage).apply()
    }

    fun get(context: Context): Storage<PlayableChar> {
        lateinit var storage: Storage<PlayableChar>
        when (getPreferencesStorage(context)) {
            PREF_STORAGE_FILE_JSON -> storage = CharJSONFileStorage.get(context)
        }

        return storage
    }

}