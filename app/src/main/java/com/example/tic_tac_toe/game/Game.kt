package com.example.tic_tac_toe.game

interface Game {
    var isFinished: Boolean
    var turn: Boolean
    val field: Field
    var winner: Boolean?
    fun act(row: Int, col: Int)
}