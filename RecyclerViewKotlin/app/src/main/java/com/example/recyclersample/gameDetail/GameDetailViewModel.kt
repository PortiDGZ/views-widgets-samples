
package com.example.recyclersample.gameDetail

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.recyclersample.data.DataSource
import com.example.recyclersample.data.Game

class GameDetailViewModel(private val datasource: DataSource) : ViewModel() {

    fun getGameForId(id: Long) : Game? {
        return datasource.getGameForId(id)
    }

    fun removeGame(game: Game) {
        datasource.removeGame(game)
    }
}

class GameDetailViewModelFactory(private val context: Context) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(GameDetailViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return GameDetailViewModel(
                datasource = DataSource.getDataSource(context.resources)
            ) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}