package com.example.tp.Stockage

import android.content.Context
import org.json.JSONObject

abstract class JSONFileStorage<T>(context: Context, name: String) :
    FileStorage<T>(context, name, EXTENSION) {
    companion object {
        private const val EXTENSION: String = ".json"
    }

    protected abstract fun objectToJson(id: Int, obj: T): JSONObject
    protected abstract fun jsonToObject(json: JSONObject): T

    override fun dataToString(data: MutableMap<Int, T>): String {
        val json = JSONObject()
        data.forEach { json.put("${it.key}", objectToJson(it.key, it.value)) }
        return json.toString()
    }

    override fun stringToData(value: String): MutableMap<Int, T> {
        val data : MutableMap<Int, T> = mutableMapOf()
        val json = JSONObject(value)
        val iterator = json.keys()
        while (iterator.hasNext()) {
            val key = iterator.next()
            data[key.toInt()] = jsonToObject(json.getJSONObject(key))
        }
        return data
    }
}