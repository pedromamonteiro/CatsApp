package com.pedromonteiro.catsapp.ui.utils

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Home
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.domain.model.Routes
import com.pedromonteiro.catsapp.domain.model.Routes.Favorites
import com.pedromonteiro.catsapp.domain.model.Routes.Home

fun Routes.getBottomNavigationIcon(selected: Routes) = when (this) {
    Home -> if (this == selected) Icons.Filled.Home else Icons.Outlined.Home
    Favorites -> if (this == selected) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder
    else -> throw IllegalArgumentException("Route is not a part of bottom navigation!")
}

fun Routes.getBottomNavigationContentDescription(selected: Routes): Int = when (this) {
    Home -> if (this == selected) R.string.already_at_home_cd else R.string.navigate_to_home_cd
    Favorites -> if (this == selected) R.string.already_at_favorites_cd else R.string.navigate_to_favorites_cd
    else -> throw IllegalArgumentException("Route is not a part of bottom navigation!")
}