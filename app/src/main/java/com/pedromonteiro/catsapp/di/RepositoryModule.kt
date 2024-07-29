package com.pedromonteiro.catsapp.di

import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.data.CatRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideCatRepository(catRepositoryImpl: CatRepositoryImpl): CatRepository =
        catRepositoryImpl
}