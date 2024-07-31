package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetFavoriteBreeds @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): Flow<List<CatBreed>> =
        repository.getFavoriteBreeds()
}