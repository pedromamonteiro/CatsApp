package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreed
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreeds
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class GetFavoriteBreeds @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): Flow<List<CatBreed>> =
        repository.getFavoriteBreeds().map { catBreedDTOS ->
            catBreedDTOS.toCatBreeds()
        }
}