package com.example.recyclersample.addGame

import android.app.Activity
import android.content.ClipDescription
import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.Button
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.recyclersample.R
import com.google.android.material.textfield.TextInputEditText

const val GAME_NAME = "name"
const val GAME_DESCRIPTION = "description"
const val GAME_SHORT_DESCRIPTION = "short description"

class AddGameActivity : AppCompatActivity() {
    private lateinit var addGameName: TextInputEditText
    private lateinit var addGameDescription: TextInputEditText
    private lateinit var addGameShortDescription: TextInputEditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_game_layout)

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addGame()
        }
        addGameName = findViewById(R.id.add_game_name)
        addGameDescription = findViewById(R.id.add_game_description)
        addGameShortDescription = findViewById(R.id.add_game_short_description)
    }

    private fun addGame() {
        val resultIntent = Intent()

        if (addGameName.text.isNullOrEmpty() || addGameDescription.text.isNullOrEmpty()) {
            setResult(Activity.RESULT_CANCELED, resultIntent)
        } else {
            val name = addGameName.text.toString()
            val description = addGameDescription.text.toString()
            val shortDescription = addGameShortDescription.text.toString()

            resultIntent.putExtra(GAME_NAME, name)
            resultIntent.putExtra(GAME_DESCRIPTION, description)
            resultIntent.putExtra(GAME_SHORT_DESCRIPTION, shortDescription)

            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
}