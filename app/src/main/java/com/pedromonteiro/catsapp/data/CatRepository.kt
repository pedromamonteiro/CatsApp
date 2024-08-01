package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import kotlinx.coroutines.flow.Flow

interface CatRepository {
    suspend fun getBreeds(): Flow<List<CatBreedDTO>>
    suspend fun getFavoriteBreeds(): Flow<List<CatBreedDTO>>
    suspend fun updateFavoriteCatBreed(catBreedId: String)
    fun getCatBreedById(catBreedId: String): Flow<CatBreedDTO>
}