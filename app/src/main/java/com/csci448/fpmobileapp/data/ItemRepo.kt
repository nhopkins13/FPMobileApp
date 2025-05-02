package com.csci448.fpmobileapp.data

import com.csci448.fpmobileapp.R

object ItemRepo {
    val topHat = ShopItem(
        name = "Top Hat",
        type = "Hat",
        imageId = R.drawable.top_hat,
        price = 10
    )
    val crown = ShopItem(
        name = "Crown",
        type = "Hat",
        imageId = 0,
        price = 30
    )
    val belt = ShopItem(
        name = "Belt",
        type = "Belt",
        imageId = 0,
        price = 10
    )
    val skirt = ShopItem(
        name = "Skirt",
        type = "Belt",
        imageId = 0,
        price = 20
    )
    val necklace = ShopItem(
        name = "Pearl Necklace",
        type = "Neckwear",
        imageId = 0,
        price = 20 
    )
}
