package com.example.recyclersample.gameList

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.ConcatAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclersample.R
import com.example.recyclersample.addGame.*
import com.example.recyclersample.data.Game
import com.example.recyclersample.gameDetail.GameDetailActivity

const val GAME_ID = "game id"

class GameListActivity : AppCompatActivity() {
    private val newGameActivityRequestCode = 1
    private val gameListViewModel by viewModels<GameListViewModel> {
        GameListViewModelFactory(this)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

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
    private fun adapterOnClick(game: Game) {
        val intent = Intent(this, GameDetailActivity()::class.java)
        intent.putExtra(GAME_ID, game.id)
        startActivity(intent)
    }

    private fun fabOnClick() {
        val intent = Intent(this, AddGameActivity::class.java)
        startActivityForResult(intent, newGameActivityRequestCode)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intentData: Intent?) {
        super.onActivityResult(requestCode, resultCode, intentData)

        if (requestCode == newGameActivityRequestCode && resultCode == Activity.RESULT_OK) {
            intentData?.let { data ->
                val gameName = data.getStringExtra(GAME_NAME)
                val gameDescription = data.getStringExtra(GAME_DESCRIPTION)
                val gameShortDescription = data.getStringExtra(GAME_SHORT_DESCRIPTION)
                gameListViewModel.insertGame(gameName, gameDescription, gameShortDescription)
            }
        }
    }
}