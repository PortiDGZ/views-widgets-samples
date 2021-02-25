package com.example.recyclersample.gameList

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R
import com.example.recyclersample.data.Game


class GameAdapter(private val onClick: (Game) -> Unit) :
    ListAdapter<Game, GameAdapter.GameViewHolder>(GameDiffCallback) {

    class GameViewHolder(itemView: View, val onClick: (Game) -> Unit) :
        RecyclerView.ViewHolder(itemView) {
        private val gameTextView: TextView = itemView.findViewById(R.id.game_text)
        private val gameImageView: ImageView = itemView.findViewById(R.id.game_image)
        private val gameShortDescriptionView: TextView = itemView.findViewById(R.id.game_short_description)
        private var currentGame: Game? = null


        init {
            itemView.setOnClickListener {
                currentGame?.let {
                    onClick(it)
                }
            }
        }

        fun bind(game: Game) {
            currentGame = game

            gameTextView.text = game.name
            gameShortDescriptionView.text = game.short_description



            if (game.image != null) {
                gameImageView.setImageResource(game.image)
            } else {
                gameImageView.setImageResource(R.drawable.checkerboard)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)
        return GameViewHolder(view, onClick)
    }

    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
        val game = getItem(position)
        holder.bind(game)

    }
}

object GameDiffCallback : DiffUtil.ItemCallback<Game>() {
    override fun areItemsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem == newItem
    }

    override fun areContentsTheSame(oldItem: Game, newItem: Game): Boolean {
        return oldItem.id == newItem.id
    }
}