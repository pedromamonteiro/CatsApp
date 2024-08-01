package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreeds
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetFavoriteBreedsTest {
    private lateinit var getFavoriteBreeds: GetFavoriteBreeds
    private val mockCatRepository = mockk<CatRepository>()

    @BeforeEach
    fun setUp() {
        getFavoriteBreeds = GetFavoriteBreeds(mockCatRepository)
    }

    @Test
    fun getFavoriteBreedsReturnsFavoriteBreeds() = runBlocking {
        val catBreedDTOS = TestHelper.generateCatBreedDTOS(amount = 10, isFavorite = true)
        val expected = catBreedDTOS.toCatBreeds()
        coEvery { mockCatRepository.getFavoriteBreeds() } returns flowOf(catBreedDTOS)

        val result = getFavoriteBreeds()

        assertEquals(result.first(), expected)
    }
}