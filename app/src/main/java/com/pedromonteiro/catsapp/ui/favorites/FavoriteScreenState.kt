package com.pedromonteiro.catsapp.ui.favorites

import com.pedromonteiro.catsapp.model.CatBreed

data class FavoriteScreenState(
    val favoriteCatBreeds: List<CatBreed> = emptyList(),
    val showRemoveFavoritePopup: Boolean = false,
    val favoriteToBeRemoved: CatBreed? = null
)
