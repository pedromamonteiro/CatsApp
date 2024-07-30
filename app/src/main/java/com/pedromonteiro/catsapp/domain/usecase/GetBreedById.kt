package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import javax.inject.Inject

class GetBreedById @Inject constructor(
    private val catRepository: CatRepository
) {
    operator fun invoke(catBreedId: String): Flow<CatBreed> =
        catRepository.getCatBreedById(catBreedId)
}