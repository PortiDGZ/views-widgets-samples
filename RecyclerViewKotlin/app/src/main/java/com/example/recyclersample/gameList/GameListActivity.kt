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

package com.example.recyclersample.gameList

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.gameDetail.GameDetailActivity
import com.example.recyclersample.R
import com.example.recyclersample.addGame.AddGameActivity
import com.example.recyclersample.addGame.GAME_DESCRIPTION
import com.example.recyclersample.addGame.GAME_NAME
import com.example.recyclersample.data.Game

const val GAME_ID = "game id"

class GameListActivity : AppCompatActivity() {
    private val newGameActivityRequestCode = 1
    private val gameListViewModel by viewModels<GameListViewModel> {
        GameListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        /* Instantiates headerAdapter and flowersAdapter. Both adapters are added to concatAdapter.
        which displays the contents sequentially */
        val headerAdapter = HeaderAdapter()
        val gameAdapter = GameAdapter { game -> adapterOnClick(game) }
        val concatAdapter = ConcatAdapter(headerAdapter, gameAdapter)

        val recyclerView: RecyclerView = findViewById(R.id.recycler_view)
        recyclerView.adapter = concatAdapter

        gameListViewModel.gameLiveData.observe(this, {
            it?.let {
                gameAdapter.submitList(it as MutableList<Game> as List<Game>?)
                headerAdapter.updateGameCount(it.size)
            }
        })

        val fab: View = findViewById(R.id.fab)
        fab.setOnClickListener {
            fabOnClick()
        }
    }

    /* Opens FlowerDetailActivity when RecyclerView item is clicked. */
    private fun adapterOnClick(game: Game) {
        val intent = Intent(this, GameDetailActivity()::class.java)
        intent.putExtra(GAME_ID, game.id)
        startActivity(intent)
    }

    /* Adds flower to flowerList when FAB is clicked. */
    private fun fabOnClick() {
        val intent = Intent(this, AddGameActivity::class.java)
        startActivityForResult(intent, newGameActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        /* Inserts flower into viewModel. */
        if (requestCode == newGameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val gameName = data.getStringExtra(GAME_NAME)
                val gameDescription = data.getStringExtra(GAME_DESCRIPTION)

                gameListViewModel.insertGame(gameName, gameDescription)
            }
        }
    }
}