package com.example.tic_tac_toe

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import com.example.tic_tac_toe.databinding.ActivityMainBinding
import com.example.tic_tac_toe.game.GameManager

class MainActivity : AppCompatActivity() {
    private lateinit var bv: ActivityMainBinding
    private lateinit var game: GameManager
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bv = ActivityMainBinding.inflate(layoutInflater)
        setContentView(bv.root)

        game = GameManager {
            val resultMessage = when(it) {
                true -> R.string.cross_win.toString()
                false -> R.string.zero_win.toString()
                null -> R.string.draw.toString()
            }
            createDialog(resultMessage)
        }

        bv.fieldRv.apply {
            layoutManager = GridLayoutManager(context, game.field.size)
            adapter = FieldAdapter(game.field) {row, col ->
                game.act(row, col)
                adapter?.notifyDataSetChanged()
            }
        }
    }

    private fun createDialog(message: String) {
        val builder = AlertDialog.Builder(this)
        builder.setTitle(message)
        builder.setNeutralButton("Restart") { _, _ ->
            game.reset()
            bv.fieldRv.adapter?.notifyDataSetChanged()
        }
        builder.show()
    }
}