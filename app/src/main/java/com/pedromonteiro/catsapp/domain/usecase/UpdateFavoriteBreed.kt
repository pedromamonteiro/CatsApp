package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import javax.inject.Inject

class UpdateFavoriteBreed @Inject constructor(
    private val catRepository: CatRepository
) {
    suspend operator fun invoke(catBreedId: String) =
        catRepository.updateFavoriteCatBreed(catBreedId)
}