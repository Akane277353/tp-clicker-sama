package com.example.tp.Stockage


import android.content.Context

import java.io.BufferedReader
import java.io.FileNotFoundException
import java.io.InputStreamReader
import java.io.OutputStreamWriter

abstract class FileStorage<T>(private val context: Context, name: String, extension: String) :
    Storage<T> {

    companion object {
        const val PREFIX: String = "storage_"
    }

    private val fileName: String = PREFIX + name + extension
    private var data: MutableMap<Int, T> = mutableMapOf()
    private var nextId: Int = 1

    init {
        read()
    }

    protected abstract fun create(id: Int, obj: T): T
    protected abstract fun dataToString(data: MutableMap<Int, T>): String
    protected abstract fun stringToData(value: String): MutableMap<Int, T>

    private fun read() {
        try {
            val input = context.openFileInput(fileName)
            println(context.filesDir)
            if (input != null) {
                val builder = StringBuilder()
                val bufferedReader = BufferedReader(InputStreamReader(input))
                var temp: String? = bufferedReader.readLine()
                while (temp != null) {
                    builder.append(temp)
                    temp = bufferedReader.readLine()
                }
                input.close()
                data = stringToData(builder.toString())
                nextId = if (max(data.keys) == null) 1 else max(data.keys)!! + 1
            }
        } catch (e: FileNotFoundException) {
            write()
        }
    }

    private fun max(keys: Set<Int>): Int? {
        var res = 0
        for (key in keys){
            if (key > res){
                res = key
            }
        }
        return if (res == 0) null else res
    }

    private fun write() {
        val output = context.openFileOutput(fileName, Context.MODE_PRIVATE)
        val writer = OutputStreamWriter(output)
        writer.write(dataToString(data))
        writer.close()
    }

    override fun insert(obj: T) {
        data[nextId] = create(nextId, obj)
        nextId++
        write()
        println()
    }

    override fun size(): Int {
        return data.size
    }

    override fun find(id: Int): T? {
        return data[id]
    }

    override fun findAll(): List<T> {
        return data.toList().map { it.second }
    }

    override fun update(id: Int, obj: T) {
        data[id] = obj
        write()
    }

    override fun delete(id: Int) {
        data.remove(id)
        write()
    }

    override fun clear() {
        data.clear()
        nextId = 0
    }
}