package com.example.tic_tac_toe.game


interface Field {
    val size: Int
    fun get(row: Int, col: Int): Boolean?
    fun set(row: Int, col: Int, value: Boolean)
    fun clear()
}