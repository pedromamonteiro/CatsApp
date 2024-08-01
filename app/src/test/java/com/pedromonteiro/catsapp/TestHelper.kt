package com.pedromonteiro.catsapp

import android.util.Log
import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreed
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreeds
import io.mockk.every
import io.mockk.mockkStatic

object TestHelper {
    const val ONCE = 1

    fun generateCatBreedDTO(isFavorite: Boolean = false): CatBreedDTO =
        generateCatBreedDTOS(1, isFavorite)[0]

    fun generateCatBreedDTOS(amount: Int = 1, isFavorite: Boolean = false): List<CatBreedDTO> {
        return List(amount) { index ->
            CatBreedDTO(
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

    fun generateCatBreed(isFavorite: Boolean = false): CatBreed =
        generateCatBreedDTO(isFavorite).toCatBreed()

    fun generateCatBreeds(amount: Int = 1, isFavorite: Boolean = false): List<CatBreed> =
        generateCatBreedDTOS(amount, isFavorite).toCatBreeds()

    fun mockLog() {
        mockkStatic(Log::class)
        every { Log.e(any(), any(), any()) } returns 0
    }
}