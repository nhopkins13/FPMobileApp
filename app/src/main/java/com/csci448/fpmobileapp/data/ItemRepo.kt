package com.csci448.fpmobileapp.data

import com.csci448.fpmobileapp.R

object ItemRepo {
    val testItem = ShopItem(
        name = "test item e.g., hat",
        type = "test type e.g. headwear",
        imageId = 0,
        price = 10
    )
    val testHat = ShopItem(
        name = "Test Hat",
        type = "Hat",
        imageId = R.drawable.some_icon,
        price = 10,
        owned = false
    )
}
