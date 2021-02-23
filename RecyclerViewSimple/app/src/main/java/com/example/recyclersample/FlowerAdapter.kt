/*
 * Copyright (C) 2020 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.recyclersample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

<<<<<<< Updated upstream
class GameAdapter(private val flowerList: Array<String>) :
=======
class GameAdapter(private val gameList: Array<String>) :
>>>>>>> Stashed changes
    RecyclerView.Adapter<GameAdapter.GameViewHolder>() {

    // Describes an item view and its place within the RecyclerView
    class GameViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
<<<<<<< Updated upstream
        private val flowerTextView: TextView = itemView.findViewById(R.id.game_text)
=======
        private val gameTextView: TextView = itemView.findViewById(R.id.game_text)
>>>>>>> Stashed changes

        fun bind(word: String) {
            gameTextView.text = word
        }
    }

    // Returns a new ViewHolder
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GameViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.game_item, parent, false)

        return GameViewHolder(view)
    }

    // Returns size of data list
    override fun getItemCount(): Int {
        return gameList.size
    }

    // Displays data at a certain position
    override fun onBindViewHolder(holder: GameViewHolder, position: Int) {
<<<<<<< Updated upstream
        holder.bind(flowerList[position])
=======
        holder.bind(gameList[position])
>>>>>>> Stashed changes
    }
}