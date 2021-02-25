

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
                gameImage.setImageResource(R.drawable.checkerboard)
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