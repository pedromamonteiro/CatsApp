package com.pedromonteiro.catsapp.data

import android.util.Log
import com.pedromonteiro.catsapp.data.database.CatBreedDao
import com.pedromonteiro.catsapp.data.remote.CatApi
import com.pedromonteiro.catsapp.model.CatBreed
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Cat repository responsible to provide data, by network or database
 */
@Singleton
class CatRepositoryImpl @Inject constructor(
    private val catApi: CatApi,
    private val catBreedDao: CatBreedDao
) : CatRepository {

    /**
     * If there's no internet connection while making the network request
     * fallbacks to use internal database
     */
    override suspend fun getBreeds(): List<CatBreed> = try {
        catApi.getBreeds()?.let { catBreeds ->
            // Insert or Update the database since the fetch is already done
            catBreedDao.insertOrUpdate(*catBreeds.toTypedArray())

            return@let catBreeds
        }.orEmpty()
    } catch (e: Exception) {
        Log.e(TAG, "Unexpected error has occurred, fallback to local cat breeds", e)
        catBreedDao.getAllCatBreeds()
    }

    private companion object {
        val TAG: String = CatRepositoryImpl::class.java.simpleName
    }
}