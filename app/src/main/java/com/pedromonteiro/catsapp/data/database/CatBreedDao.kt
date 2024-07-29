package com.pedromonteiro.catsapp.data.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.pedromonteiro.catsapp.model.CatBreed

@Dao
interface CatBreedDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertOrUpdate(vararg catBreed: CatBreed)

    @Query("SELECT * FROM catBreed ORDER BY name ASC")
    suspend fun getAllCatBreeds(): List<CatBreed>
}