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

class GetBreedsTest {
    private lateinit var getBreeds: GetBreeds
    private val mockCatRepository = mockk<CatRepository>()

    @BeforeEach
    fun setUp() {
        getBreeds = GetBreeds(mockCatRepository)
    }

    @Test
    fun getBreedsReturnsBreeds() = runBlocking {
        val catBreedDTOS = TestHelper.generateCatBreedDTOS(10)
        val expected = catBreedDTOS.toCatBreeds()
        coEvery { mockCatRepository.getBreeds() } returns flowOf(catBreedDTOS)

        val result = getBreeds()

        assertEquals(result.first(), expected)
    }
}