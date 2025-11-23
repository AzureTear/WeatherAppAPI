package com.example.weatherapp.navigation

object Routes {
    const val HOME = "home"
    const val OTHERS = "see others"

    const val ADD = "add"
    const val DETAIL = "detail/{itemId}"
    fun detailRoute(itemId: Int) = "detail/$itemId"
}