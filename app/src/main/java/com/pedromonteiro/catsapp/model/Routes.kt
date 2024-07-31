package com.pedromonteiro.catsapp.model

enum class Routes(val route: String) {
    Home("home"),
    Favorites("favorites"),
    Details("details/{catBreedId}");

    companion object {
        val BOTTOM_NAVIGATION_ROUTES = listOf(Home, Favorites)
        val TOP_CAT_BAR_ROUTES = listOf(Home, Favorites)
    }
}