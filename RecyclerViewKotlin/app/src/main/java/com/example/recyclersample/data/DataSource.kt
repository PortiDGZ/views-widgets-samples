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

package com.example.recyclersample.data

import android.content.res.Resources
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

/* Handles operations on flowersLiveData and holds details about it. */
class DataSource(resources: Resources) {
    private val initialGameList = gameList(resources)
    private val gameLiveData = MutableLiveData(initialGameList)

    /* Adds flower to liveData and posts value. */
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

    /* Removes flower from liveData and posts value. */
    fun removeGame(game: Game) {
        val currentList = gameLiveData.value
        if (currentList != null) {
            val updatedList = currentList.toMutableList()
            updatedList.remove(game)
            gameLiveData.postValue(updatedList)
        }
    }

    /* Returns flower given an ID. */
    fun getGameForId(id: Long): Game? {
        gameLiveData.value?.let { game ->
            return game.firstOrNull{ it.id == id}
        }
        return null
    }

    fun getGameList(): LiveData<List<Game>> {
        return gameLiveData
    }

    /* Returns a random flower asset for flowers that are added. */
    fun getRandomGameImageAsset(): Int? {
        val randomNumber = (initialGameList.indices).random()
        return initialGameList[randomNumber].image
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