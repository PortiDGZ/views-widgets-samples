
package com.example.recyclersample.data

import androidx.annotation.DrawableRes

data class Game(
    val id: Long,
    val name: String,
    @DrawableRes
    val image: Int?,
    val description: String,
    val short_description: String
)