package com.example.tic_tac_toe.game


class ArrayField(override val size: Int): Field {
    private var points: Array<Array<Boolean?>> = Array(size) { arrayOfNulls(size) }
    override fun get(row: Int, col: Int) = points[row][col]
    override fun set(row: Int, col: Int, value: Boolean) {
        points[row][col] = value
    }

    override fun clear() {
        points = Array(size) { arrayOfNulls(size) }
    }
}