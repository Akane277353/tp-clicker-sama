package com.example.tp.Stockage

interface Storage<T> {
    fun insert(obj: T)

    fun size(): Int

    fun find(id: Int) : T?

    fun findAll() : List<T>

    fun update(id: Int, obj : T)

    fun delete(id : Int)

    fun clear()
}