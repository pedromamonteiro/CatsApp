package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.model.CatBreed
import retrofit2.http.GET

interface CatApi {
    @GET("v1/breeds")
    suspend fun getBreeds() : List<CatBreed>?
}