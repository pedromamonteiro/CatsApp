package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.model.CatBreed
import javax.inject.Inject

class GetBreeds @Inject constructor(
    private val repository: CatRepository
) {
    suspend operator fun invoke(): List<CatBreed> =
        repository.getBreeds()
}