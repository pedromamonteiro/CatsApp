package com.pedromonteiro.catsapp.data.remote

import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import retrofit2.http.GET

interface CatApi {
    @GET("v1/breeds")
    suspend fun getBreeds() : List<CatBreedDTO>?
}