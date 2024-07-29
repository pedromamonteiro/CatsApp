package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.model.CatBreed

interface CatRepository {
    suspend fun getBreeds(): List<CatBreed>
}