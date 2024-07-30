package com.pedromonteiro.catsapp.ui.home

import com.pedromonteiro.catsapp.model.CatBreed

data class HomeScreenState(
    val catBreeds: List<CatBreed> = emptyList(),
    val searchString: String = ""
)
