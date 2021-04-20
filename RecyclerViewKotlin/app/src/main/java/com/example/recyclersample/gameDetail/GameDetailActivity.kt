

package com.example.recyclersample.gameDetail

import android.app.SearchManager
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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
        val shareButton: Button = findViewById(R.id.shareBtn)
        val sendButton: Button = findViewById(R.id.sendBtn)
        val findButton: Button = findViewById(R.id.findBtn)

        sendButton.setOnClickListener{

            val recipient = "jcarco@gmail.com"
            val subject = gameName.text.toString().trim()
            val message = gameDescription.text.toString().trim()

            sendEmail(recipient, subject, message)


        }

        shareButton.setOnClickListener {

            val sendIntent: Intent = Intent().apply {
                action = Intent.ACTION_SEND
                putExtra(Intent.EXTRA_TEXT, gameName.text.toString())
                putExtra(gameName.text.toString(), "Título")
                type = "text/plain"
            }

            val shareIntent = Intent.createChooser(sendIntent, null)
            startActivity(shareIntent)

        }


        findButton.setOnClickListener {
            val viewSearch = Intent(Intent.ACTION_WEB_SEARCH)
            viewSearch.putExtra(SearchManager.QUERY, gameName.text.toString())
            startActivity(viewSearch)


        }

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

    private fun sendEmail(recipient: String, subject: String, message: String) {

        val mIntent = Intent(Intent.ACTION_SEND)

        mIntent.data = Uri.parse("mailto:")
        mIntent.type = "message/rfc822"

        mIntent.putExtra(Intent.EXTRA_EMAIL, arrayOf(recipient))
        mIntent.putExtra(Intent.EXTRA_SUBJECT, subject)
        mIntent.putExtra(Intent.EXTRA_TEXT, message)


        try {
            startActivity(Intent.createChooser(mIntent, "Elige el cliente de correo"))
        }
        catch (e: Exception){

            Toast.makeText(this, e.message, Toast.LENGTH_LONG).show()
        }

    }
}