package com.example.recyclersample.data

import android.content.Intent
import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.recyclersample.R

class DataSource(resources: Resources) {
    private val initialGameList = gameList(resources)
    private val gameLiveData = MutableLiveData(initialGameList)

    fun addGame(game: Game) {
        val currentList = gameLiveData.value
        if (currentList == null) {
            gameLiveData.postValue(listOf(game))
        } else {
            val updatedList = currentList.toMutableList()
            updatedList.add(0, game)
            gameLiveData.postValue(updatedList)
        }
    }

    fun removeGame(game: Game) {
        val currentList = gameLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(game)
            gameLiveData.postValue(updatedList)
        }
    }

    fun getGameForId(id: Long): Game? {
        gameLiveData.value?.let { game ->
            return game.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getGameList(): LiveData<List<Game>> {
        return gameLiveData
    }

    fun getImageAsset(): Int {
        return R.drawable.checkerboard
    }

    companion object {
        private var INSTANCE: DataSource? = null

        fun getDataSource(resources: Resources): DataSource {
            return synchronized(DataSource::class) {
                val newInstance = INSTANCE ?: DataSource(resources)
                INSTANCE = newInstance
                newInstance
            }
        }
    }
}