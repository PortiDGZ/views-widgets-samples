
package com.example.recyclersample.data

import android.content.res.Resources
import com.example.recyclersample.R

fun gameList(resources: Resources): List<Game> {
    return listOf(
        Game(
            id = 1,
            name = resources.getString(R.string.game1_name),
            image = R.drawable.battlefield3_caratula,
            description = resources.getString(R.string.game1_description),
            short_description = resources.getString(R.string.game1_short_description)
        ),
        Game(
            id = 2,
            name = resources.getString(R.string.game2_name),
            image = R.drawable.bdoremaster,
            description = resources.getString(R.string.game2_description),
            short_description = resources.getString(R.string.game2_short_description)
        ),
        Game(
            id = 3,
            name = resources.getString(R.string.game3_name),
            image = R.drawable.wow,
            description = resources.getString(R.string.game3_description),
            short_description = resources.getString(R.string.game3_short_description)
        ),
        Game(
            id = 4,
            name = resources.getString(R.string.game4_name),
            image = R.drawable.bf4,
            description = resources.getString(R.string.game4_description),
            short_description = resources.getString(R.string.game4_short_description)
        ),
        Game(
            id = 5,
            name = resources.getString(R.string.game5_name),
            image = R.drawable.mw2,
            description = resources.getString(R.string.game5_description),
            short_description = resources.getString(R.string.game5_short_description)
        ),
        Game(
            id = 6,
            name = resources.getString(R.string.game6_name),
            image = R.drawable.momodora,
            description = resources.getString(R.string.game6_description),
            short_description = resources.getString(R.string.game6_short_description)
        ),
        Game(
            id = 7,
            name = resources.getString(R.string.game7_name),
            image = R.drawable.hollow,
            description = resources.getString(R.string.game7_description),
            short_description = resources.getString(R.string.game7_short_description)
        ),
        Game(
            id = 8,
            name = resources.getString(R.string.game8_name),
            image = R.drawable.dark_souls,
            description = resources.getString(R.string.game8_description),
            short_description = resources.getString(R.string.game8_short_description)
        ),
        Game(
            id = 9,
            name = resources.getString(R.string.game9_name),
            image = R.drawable.satisfactory,
            description = resources.getString(R.string.game9_description),
            short_description = resources.getString(R.string.game9_short_description)
        ),
        Game(
            id = 10,
            name = resources.getString(R.string.game10_name),
            image = R.drawable.minecraft,
            description = resources.getString(R.string.game10_description),
            short_description = resources.getString(R.string.game10_short_description)
        ),
        Game(
            id = 11,
            name = resources.getString(R.string.game11_name),
            image = R.drawable.borderlands3,
            description = resources.getString(R.string.game11_description),
            short_description = resources.getString(R.string.game11_short_description)
        )
    )
}