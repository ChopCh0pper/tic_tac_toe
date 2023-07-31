package com.example.tic_tac_toe.game

class GameManager(private val gameListener: (winner: Boolean?) -> Unit) : Game {
    override var isFinished: Boolean = false
    override val field: Field = ArrayField(3)
    override var winner: Boolean? = null
    override var turn: Boolean = true

    override fun act(row: Int, col: Int) {
        if (!isFinished) {
            if (field.get(row, col) == null) {
                turn = when (turn) {
                    true -> {
                        field.set(row, col, true)
                        checkForWin(row, col)
                        false
                    }
                    false -> {
                        field.set(row, col, false)
                        checkForWin(row, col)
                        true
                    }
                }
            }
        }
    }

    private fun checkForWin(row: Int, col: Int) {
        // Check for horizontal win
        var horizontalWin = true
        for (c in 0 until field.size) {
            if (field.get(row, c) != turn) {
                horizontalWin = false
                break
            }
        }

        // Check for vertical win
        var verticalWin = true
        for (r in 0 until field.size) {
            if (field.get(r, col) != turn) {
                verticalWin = false
                break
            }
        }

        // Check for diagonal win (top-left to bottom-right)
        var diagonalWin1 = true
        if (row == col) {
            for (i in 0 until field.size) {
                if (field.get(i, i) != turn) {
                    diagonalWin1 = false
                    break
                }
            }
        } else {
            diagonalWin1 = false
        }

        // Check for diagonal win (top-right to bottom-left)
        var diagonalWin2 = true
        if (row + col == field.size - 1) {
            for (i in 0 until field.size) {
                if (field.get(i, field.size - i - 1) != turn) {
                    diagonalWin2 = false
                    break
                }
            }
        } else {
            diagonalWin2 = false
        }

        // Check if any of the win conditions are met
        if (horizontalWin || verticalWin || diagonalWin1 || diagonalWin2) {
            isFinished = true
            winner = turn
            gameListener(winner)
        } else if (fieldIsFull()) {
            // If the field is full and no winner, it's a draw
            isFinished = true
            winner = null
            gameListener(winner)
        }
    }

    private fun fieldIsFull(): Boolean {
        for (row in 0 until field.size) {
            for (col in 0 until field.size) {
                if (field.get(row, col) == null) {
                    return false
                }
            }
        }
        return true
    }

    fun reset() {
        isFinished = false
        winner = null
        turn = true
        field.clear()
    }

}

fun Boolean?.toMark(): String = when (this) {
    true -> "X"
    false -> "0"
    null -> "-"
}
