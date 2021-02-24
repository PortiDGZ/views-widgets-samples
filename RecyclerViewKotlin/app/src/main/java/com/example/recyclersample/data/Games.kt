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
import com.example.recyclersample.R

/* Returns initial list of flowers. */
fun gameList(resources: Resources): List<Game> {
    return listOf(
        Game(
            id = 1,
            name = resources.getString(R.string.game1_name),
            image = R.drawable.rose,
            description = resources.getString(R.string.game1_description)
        ),
        Game(
            id = 2,
            name = resources.getString(R.string.game2_name),
            image = R.drawable.freesia,
            description = resources.getString(R.string.game2_description)
        ),
        Game(
            id = 3,
            name = resources.getString(R.string.game3_name),
            image = R.drawable.lily,
            description = resources.getString(R.string.game3_description)
        ),
        Game(
            id = 4,
            name = resources.getString(R.string.game4_name),
            image = R.drawable.sunflower,
            description = resources.getString(R.string.game4_description)
        ),
        Game(
            id = 5,
            name = resources.getString(R.string.game5_name),
            image = R.drawable.peony,
            description = resources.getString(R.string.game5_description)
        ),
        Game(
            id = 6,
            name = resources.getString(R.string.game6_name),
            image = R.drawable.daisy,
            description = resources.getString(R.string.game6_description)
        ),
        Game(
            id = 7,
            name = resources.getString(R.string.game7_name),
            image = R.drawable.lilac,
            description = resources.getString(R.string.game7_description)
        ),
        Game(
            id = 8,
            name = resources.getString(R.string.game8_name),
            image = R.drawable.marigold,
            description = resources.getString(R.string.game8_description)
        ),
        Game(
            id = 9,
            name = resources.getString(R.string.game9_name),
            image = R.drawable.poppy,
            description = resources.getString(R.string.game9_description)
        ),
        Game(
            id = 10,
            name = resources.getString(R.string.game10_name),
            image = R.drawable.daffodil,
            description = resources.getString(R.string.game10_description)
        ),
        Game(
            id = 11,
            name = resources.getString(R.string.game11_name),
            image = R.drawable.dahlia,
            description = resources.getString(R.string.game11_description)
        ),
        Game(
             id = 11,
             name = resources.getString(R.string.game12_name),
             image = R.drawable.dahlia,
             description = resources.getString(R.string.game12_description)
            )
    )
}