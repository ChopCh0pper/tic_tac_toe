package com.example.tic_tac_toe

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.tic_tac_toe.game.Field
import com.example.tic_tac_toe.game.toMark

class FieldAdapter(
    private val field: Field,
    private val onClick: (row: Int, col: Int) -> Unit
    ): RecyclerView.Adapter<ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(parent, onClick)
    }

    override fun getItemCount(): Int = field.size * field.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val row = position / field.size
        val col = position % field.size
        holder.bind(field.get(row, col), row, col)
    }

}

class ViewHolder(parent: ViewGroup, onClick: (row: Int, col: Int) -> Unit): RecyclerView.ViewHolder(
    LayoutInflater.from(parent.context).inflate(R.layout.field_cell, parent, false)
) {
    private val cellTv = itemView.findViewById<TextView>(R.id.cell)
    private var row = -1
    private var col = -1

    init {
        cellTv.setOnClickListener{
            onClick(row, col)
        }
    }

    fun bind(cell: Boolean?, row: Int, col: Int) {
        this.row = row
        this.col = col
        cellTv.text= cell.toMark()
        Log.d("tag", cell.toString())
    }
}