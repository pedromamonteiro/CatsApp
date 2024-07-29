package com.pedromonteiro.catsapp.data.remote

import android.util.Log
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class CatApiImpl @Inject constructor(
    okHttpClient: OkHttpClient
) : CatApi {

    private val service = Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
        .create<CatApi>()

    override suspend fun getBreeds() =
        service.getBreeds()

    private companion object {
        const val BASE_URL = "https://api.thecatapi.com/"
    }
}