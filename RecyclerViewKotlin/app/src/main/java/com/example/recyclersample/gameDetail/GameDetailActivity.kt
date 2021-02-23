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

package com.example.recyclersample.gameDetail

import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersample.R
import com.example.recyclersample.gameList.GAME_ID

class GameDetailActivity : AppCompatActivity() {

    private val GameDetailViewModel by viewModels<GameDetailViewModel> {
        GameDetailViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.game_detail_activity)

        var currentGameId: Long? = null

        val gameName: TextView = findViewById(R.id.game_detail_name)
        val gameImage: ImageView = findViewById(R.id.game_detail_image)
        val gameDescription: TextView = findViewById(R.id.game_detail_description)
        val removeGameButton: Button = findViewById(R.id.remove_button)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            currentGameId = bundle.getLong(GAME_ID)
        }


        currentGameId?.let {
            val currentGame = GameDetailViewModel.getGameForId(it)
            gameName.text = currentGame?.name
            if (currentGame?.image == null) {
                gameImage.setImageResource(R.drawable.rose)
            } else {
                gameImage.setImageResource(currentGame.image)
            }
            gameDescription.text = currentGame?.description

            removeGameButton.setOnClickListener {
                if (currentGame != null) {
                    GameDetailViewModel.removeGame(currentGame)
                }
                finish()
            }
        }

    }
}