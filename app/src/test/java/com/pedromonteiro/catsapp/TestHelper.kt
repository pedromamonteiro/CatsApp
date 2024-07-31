package com.pedromonteiro.catsapp

import android.util.Log
import com.pedromonteiro.catsapp.domain.model.CatBreed
import io.mockk.every
import io.mockk.mockkStatic

object TestHelper {
    const val ONCE = 1
    const val TWICE = 2

    fun generateCatBreed(isFavorite: Boolean = false): CatBreed =
        generateCatBreeds(1, isFavorite)[0]

    fun generateCatBreeds(amount: Int = 1, isFavorite: Boolean = false): List<CatBreed> {
        return List(amount) { index ->
            CatBreed(
                id = "$index",
                name = "Abyssiniaan-$index",
                referenceImageId = "0XYvRd7oD",
                lifeSpan = "14 - 15",
                origin = "Egypt",
                temperament = "",
                description = "",
                isFavorite = isFavorite
            )
        }
    }

    fun mockLog() {
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }
}