package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.data.CatRepository
import com.pedromonteiro.catsapp.domain.model.CatBreed.Companion.toCatBreed
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class GetBreedByIdTest {
    private lateinit var getBreedById: GetBreedById
    private val mockCatRepository = mockk<CatRepository>()

    @BeforeEach
    fun setUp() {
        getBreedById = GetBreedById(mockCatRepository)
    }

    @Test
    fun getBreedByIdReturnsBreed() = runBlocking {
        val catBreedDTO = TestHelper.generateCatBreedDTO()
        val expected = catBreedDTO.toCatBreed()
        every { mockCatRepository.getCatBreedById(any()) } returns flowOf(catBreedDTO)

        val result = getBreedById(expected.id)

        assertEquals(result.first(), expected)
    }
}