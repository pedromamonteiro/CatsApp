package com.pedromonteiro.catsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import javax.inject.Singleton

@Database(entities = [CatBreedDTO::class], version = 1, exportSchema = false)
@Singleton
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao
}