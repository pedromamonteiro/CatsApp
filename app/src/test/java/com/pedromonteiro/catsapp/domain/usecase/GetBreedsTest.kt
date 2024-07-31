package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.data.CatRepository
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
        val expected = TestHelper.generateCatBreeds(10)
        coEvery { mockCatRepository.getBreeds() } returns flowOf(expected)

        val result = getBreeds()

        assertEquals(result.first(), expected)
    }
}