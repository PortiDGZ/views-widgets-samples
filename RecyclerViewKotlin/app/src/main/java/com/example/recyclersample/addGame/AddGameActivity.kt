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
const val GAME_IMAGE = "image"

class AddGameActivity : AppCompatActivity() {
    private val RESULT_LOAD_IMAGE = 1
    lateinit var selectedImage: Uri
    private lateinit var addGameName: TextInputEditText
    private lateinit var addGameDescription: TextInputEditText
    private lateinit var addGameShortDescription: TextInputEditText
    private lateinit var picturePath: String
    private lateinit var  addGamePhoto: Button
    private lateinit var gameDescPhoto: ImageView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.add_game_layout)
        picturePath = ""

        findViewById<Button>(R.id.done_button).setOnClickListener {
            addGame()
        }
        addGameName = findViewById(R.id.add_game_name)
        addGameDescription = findViewById(R.id.add_game_description)
        addGameShortDescription = findViewById(R.id.add_game_short_description)
        addGamePhoto = findViewById(R.id.upload_photoBtn)
        gameDescPhoto = findViewById(R.id.game_add_image)
        addGamePhoto.setOnClickListener {
            val i = Intent(
                    Intent.ACTION_PICK,
            )
            i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(i, RESULT_LOAD_IMAGE)
        }
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
            resultIntent.putExtra(GAME_IMAGE, picturePath)

            setResult(Activity.RESULT_OK, resultIntent)
        }
        finish()
    }
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == RESULT_LOAD_IMAGE && resultCode == RESULT_OK && null != data) {
            selectedImage = data.data!!
            val filePathColumn = arrayOf(MediaStore.Images.Media.DATA)
            val cursor: Cursor? = selectedImage.let {
                contentResolver.query(
                        it,
                        filePathColumn, null, null, null
                )
            }
            cursor?.moveToFirst()
            val columnIndex: Int? = cursor?.getColumnIndex(filePathColumn[0])
            picturePath = (columnIndex?.let { cursor.getString(it) } ?: cursor?.close()) as String
            gameDescPhoto.setImageBitmap(BitmapFactory.decodeFile(picturePath))
        }
    }
}