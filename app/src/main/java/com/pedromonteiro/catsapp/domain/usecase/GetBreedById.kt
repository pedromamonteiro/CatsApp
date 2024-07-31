package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetBreedById @Inject constructor(
    private val catRepository: CatRepository
) {
    operator fun invoke(catBreedId: String): Flow<CatBreed> =
        catRepository.getCatBreedById(catBreedId)
}