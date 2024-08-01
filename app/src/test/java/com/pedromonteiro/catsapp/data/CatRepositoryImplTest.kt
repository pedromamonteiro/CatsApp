package com.pedromonteiro.catsapp.data

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.TestHelper.ONCE
import com.pedromonteiro.catsapp.data.database.CatBreedDao
import com.pedromonteiro.catsapp.data.remote.CatApi
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.every
import io.mockk.just
import io.mockk.mockk
import io.mockk.unmockkAll
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class CatRepositoryImplTest {
    private lateinit var catRepository: CatRepository
    private val mockCatApi = mockk<CatApi>()
    private val mockCatBreedDao = mockk<CatBreedDao>()

    @BeforeEach
    fun setUp() {
        catRepository = CatRepositoryImpl(mockCatApi, mockCatBreedDao)
    }

    @AfterEach
    fun tearDown() {
        unmockkAll()
    }

    @Test
    fun getBreedsRequest_Succeeds_ShouldCache_ValidateFavoritesData_StoreDatabase_ReturnBreeds() =
        runBlocking {
            val expected = TestHelper.generateCatBreedDTOS(10)
            val expectedFavorites = TestHelper.generateCatBreedDTOS(10, true)
            coEvery { mockCatApi.getBreeds() } returns expected
            every { mockCatBreedDao.getAllFavoriteCatBreeds() } returns flowOf(expectedFavorites)
            coEvery { mockCatBreedDao.insertOrUpdate(any()) } just Runs
            every { mockCatBreedDao.getAllCatBreeds() } returns flowOf(expectedFavorites)

            val result = catRepository.getBreeds()

            assertEquals(result.first(), expectedFavorites)
        }

    @Test
    fun getBreedsRequest_Fails_FallbackDatabase_ReturnBreeds() = runBlocking {
        val expected = TestHelper.generateCatBreedDTOS(10)
        coEvery { mockCatApi.getBreeds() } returns null
        every { mockCatBreedDao.getAllCatBreeds() } returns flowOf(expected)

        val result = catRepository.getBreeds()

        assertEquals(result.first(), expected)
    }

    @Test
    fun getBreedsRequest_Throws_FallbackDatabase_ReturnBreeds() = runBlocking {
        val expected = TestHelper.generateCatBreedDTOS(10)
        coEvery { mockCatApi.getBreeds() } throws Exception("test")
        every { mockCatBreedDao.getAllCatBreeds() } returns flowOf(expected)
        TestHelper.mockLog()

        val result = catRepository.getBreeds()

        assertEquals(result.first(), expected)
    }

    @Test
    fun getFavoriteBreeds_ReturnFavoriteBreeds() = runBlocking {
        val expected = TestHelper.generateCatBreedDTOS(10, true)
        every { mockCatBreedDao.getAllFavoriteCatBreeds() } returns flowOf(expected)

        val result = catRepository.getFavoriteBreeds()

        assertEquals(result.first(), expected)
    }

    @Test
    fun updateFavoriteCatBreed_UpdatesBreed() = runBlocking {
        val fakeId = "test"
        coEvery { mockCatBreedDao.updateFavoriteCatBreed(any()) } just Runs

        catRepository.updateFavoriteCatBreed(fakeId)

        coVerify(exactly = ONCE) { mockCatBreedDao.updateFavoriteCatBreed(fakeId) }
    }

    @Test
    fun getCatBreedById_ReturnCatBreed() = runBlocking {
        val expected = TestHelper.generateCatBreedDTO()
        val fakeId = expected.id
        every { mockCatBreedDao.getCatBreedById(any()) } returns flowOf(expected)

        val result = catRepository.getCatBreedById(fakeId)

        assertEquals(result.first(), expected)
    }
}