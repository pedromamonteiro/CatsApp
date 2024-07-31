package com.pedromonteiro.catsapp.domain.usecase

import com.pedromonteiro.catsapp.TestHelper.ONCE
import com.pedromonteiro.catsapp.data.CatRepository
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

class UpdateFavoriteBreedTest {
    private lateinit var updateFavoriteBreed: UpdateFavoriteBreed
    private val mockCatRepository = mockk<CatRepository>()

    @BeforeEach
    fun setUp() {
        updateFavoriteBreed = UpdateFavoriteBreed(mockCatRepository)
    }

    @Test
    fun updateFavoriteBreedUpdatesBreed() = runBlocking {
        val fakeId = "test"
        coEvery { mockCatRepository.updateFavoriteCatBreed(any()) } just Runs

        updateFavoriteBreed(fakeId)

        coVerify(exactly = ONCE) {
            mockCatRepository.updateFavoriteCatBreed(fakeId)
        }
    }
}