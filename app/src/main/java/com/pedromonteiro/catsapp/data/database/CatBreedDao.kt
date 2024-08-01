package com.pedromonteiro.catsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import kotlinx.coroutines.flow.Flow

@Dao
interface CatBreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(catBreedDTOS: List<CatBreedDTO>)

    @Query("SELECT * FROM catBreed ORDER BY name ASC")
    fun getAllCatBreeds(): Flow<List<CatBreedDTO>>

    @Query("SELECT * FROM catBreed WHERE isFavorite = 1 ORDER BY name ASC")
    fun getAllFavoriteCatBreeds(): Flow<List<CatBreedDTO>>

    @Query("UPDATE catBreed SET isFavorite = NOT isFavorite WHERE id = :catBreedId")
    suspend fun updateFavoriteCatBreed(catBreedId: String)

    @Query("SELECT * FROM catBreed WHERE id = :catBreedId")
    fun getCatBreedById(catBreedId: String): Flow<CatBreedDTO>
}