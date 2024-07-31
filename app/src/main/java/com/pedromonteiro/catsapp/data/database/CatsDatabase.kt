package com.pedromonteiro.catsapp.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.pedromonteiro.catsapp.domain.model.CatBreed
import javax.inject.Singleton

@Database(entities = [CatBreed::class], version = 1, exportSchema = false)
@Singleton
abstract class CatsDatabase : RoomDatabase() {
    abstract fun catBreedDao(): CatBreedDao
}