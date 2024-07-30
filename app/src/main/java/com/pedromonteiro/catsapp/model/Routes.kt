package com.pedromonteiro.catsapp.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import com.pedromonteiro.catsapp.R

enum class Routes(val route: String) {
    Home("home"),
    Favorites("favorites"),
    Details("details/{catBreedId}");

    fun getBottomNavigationIcon(selected: Routes) = when (this) {
        Home -> if (this == selected) Icons.Filled.Home else Icons.Outlined.Home
        Favorites -> if (this == selected) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
        else -> throw IllegalArgumentException("Route is not a part of bottom navigation!")
    }

    fun getBottomNavigationContentDescription(selected: Routes): Int = when (this) {
        Home -> if (this == selected) R.string.already_at_home_cd else R.string.navigate_to_home_cd
        Favorites -> if (this == selected) R.string.already_at_favorites_cd else R.string.navigate_to_favorites_cd
        else -> throw IllegalArgumentException("Route is not a part of bottom navigation!")
    }

    fun hasTopBackButton(): Boolean =
        this == Details

    fun hasTopFavoriteButton(): Boolean =
        this == Details

    companion object {
        val BOTTOM_NAVIGATION_ROUTES = listOf(Home, Favorites)
    }
}