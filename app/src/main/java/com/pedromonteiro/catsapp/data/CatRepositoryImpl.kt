package com.pedromonteiro.catsapp.data

import android.util.Log
import com.pedromonteiro.catsapp.data.database.CatBreedDao
import com.pedromonteiro.catsapp.data.remote.CatApi
import com.pedromonteiro.catsapp.domain.model.CatBreed
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.firstOrNull
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
     * Provides an list of Cat Breeds.
     * Saves to internal database whenever a successful internet connection occurs, and returns those values.
     * If there's an error while making the network request fallbacks to use internal database.
     */
    override suspend fun getBreeds(): Flow<List<CatBreed>> = try {
        catApi.getBreeds()?.let { catBreeds ->
            // Since there's no mechanism of checking if what we have on database is the same as in the server
            // check if the user previously added the breed as favorite and update favorite state
            val favoriteBreedsIds =
                catBreedDao.getAllFavoriteCatBreeds().firstOrNull()?.map { it.id } ?: emptyList()
            val favoriteUpdatedBreeds = catBreeds.map { catBreed ->
                catBreed.copy(isFavorite = favoriteBreedsIds.contains(catBreed.id))
            }

            catBreedDao.insertOrUpdate(favoriteUpdatedBreeds)

            return@let catBreedDao.getAllCatBreeds()
        } ?: catBreedDao.getAllCatBreeds()
    } catch (e: Exception) {
        Log.e(TAG, "Unexpected error has occurred, fallback to local cat breeds", e)
        catBreedDao.getAllCatBreeds()
    }

    /**
     * Provides an list of favorite Cat Breeds, obtained from the Database, that the user marked as favorite.
     */
    override suspend fun getFavoriteBreeds(): Flow<List<CatBreed>> =
        catBreedDao.getAllFavoriteCatBreeds()

    /**
     * Updates the Favorite value of a Cat Breed in the database
     */
    override suspend fun updateFavoriteCatBreed(catBreedId: String) =
        catBreedDao.updateFavoriteCatBreed(catBreedId)

    /**
     * Returns the an Cat Breed by the specified Id.
     */
    override fun getCatBreedById(catBreedId: String): Flow<CatBreed> =
        catBreedDao.getCatBreedById(catBreedId)

    private companion object {
        val TAG: String = CatRepositoryImpl::class.java.simpleName
    }
}