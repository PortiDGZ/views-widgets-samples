package com.example.recyclersample.gameList

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclersample.data.DataSource
import com.example.recyclersample.data.Game
import kotlin.random.Random

class GameListViewModel(val dataSource: DataSource) : ViewModel() {

    val gameLiveData = dataSource.getGameList()

    fun insertGame(gameName: String?, gameDescription: String?, gameShortDescription: String?) {
        if (gameName == null || gameDescription == null || gameShortDescription == null) {
            return
        }

        val image = dataSource.getImageAsset()
        val newGame = Game(
            Random.nextLong(),
            gameName,
            image,
            gameDescription,
            gameShortDescription
        )

        dataSource.addGame(newGame)
    }
}

class GameListViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameListViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameListViewModel(
                dataSource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}