package com.pedromonteiro.catsapp.di

import android.content.Context
import androidx.room.Room
import com.pedromonteiro.catsapp.data.database.CatBreedDao
import com.pedromonteiro.catsapp.data.database.CatsDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideCatsDatabase(@ApplicationContext appContext: Context): CatsDatabase =
        Room
            .databaseBuilder(
                appContext,
                CatsDatabase::class.java,
                "Cats"
            )
            .build()

    @Provides
    @Singleton
    fun provideCatBreedDao(catsDatabase: CatsDatabase): CatBreedDao =
        catsDatabase.catBreedDao()
}