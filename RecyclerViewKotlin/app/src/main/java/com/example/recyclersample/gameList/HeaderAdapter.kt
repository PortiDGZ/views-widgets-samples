package com.example.recyclersample.gameList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R


class HeaderAdapter: RecyclerView.Adapter<HeaderAdapter.HeaderViewHolder>() {
    private var gameCount: Int = 0

    class HeaderViewHolder(view: View) : RecyclerView.ViewHolder(view){
        private val gameNumberTextView: TextView = itemView.findViewById(R.id.game_number_text)

        fun bind(gameCount: Int) {
            gameNumberTextView.text = gameCount.toString()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HeaderViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.header_item, parent, false)
        return HeaderViewHolder(view)
    }

    override fun onBindViewHolder(holder: HeaderViewHolder, position: Int) {
        holder.bind(gameCount)
    }

    /* Returns number of items, since there is only one item in the header return one  */
    override fun getItemCount(): Int {
        return 1
    }

    /* Updates header to display number of flowers when a flower is added or subtracted. */
    fun updateGameCount(updatedGameCount: Int) {
        gameCount = updatedGameCount
        notifyDataSetChanged()
    }
}