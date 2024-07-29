package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.model.CatBreed
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Cat repository responsible to provide data, by network or database
 */
@Singleton
class CatRepositoryImpl @Inject constructor(
    private val catApi: CatApi
) : CatRepository {

    // TODO add later logic about offline usage here on this repository
    //  also add a flag stating if user is online or offline so this responsibility isn't handled by the repository
    override suspend fun getBreeds(): List<CatBreed> =
        catApi.getBreeds() ?: emptyList()
}