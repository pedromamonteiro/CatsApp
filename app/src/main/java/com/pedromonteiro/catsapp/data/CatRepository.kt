package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun getBreeds(): Flow<List<CatBreed>>
    suspend fun getFavoriteBreeds(): Flow<List<CatBreed>>
    suspend fun updateFavoriteCatBreed(catBreedId: String)
    fun getCatBreedById(catBreedId: String): Flow<CatBreed>
}