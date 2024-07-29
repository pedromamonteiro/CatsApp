package com.pedromonteiro.catsapp.di

import com.pedromonteiro.catsapp.data.CatApi
import com.pedromonteiro.catsapp.data.CatApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient() =
        OkHttpClient.Builder().build()

    @Provides
    fun provideCatApi(okHttpClient: OkHttpClient): CatApi =
        CatApiImpl(okHttpClient)
}