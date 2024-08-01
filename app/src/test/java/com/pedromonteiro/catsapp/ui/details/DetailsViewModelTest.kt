package com.pedromonteiro.catsapp.ui.details

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.TestHelper.ONCE
import com.pedromonteiro.catsapp.domain.usecase.GetBreedById
import com.pedromonteiro.catsapp.domain.usecase.UpdateFavoriteBreed
import io.mockk.Runs
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.just
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class DetailsViewModelTest {
    private lateinit var detailsViewModel: DetailsViewModel
    private val mockGetBreedById = mockk<GetBreedById>()
    private val mockUpdateFavoriteBreed = mockk<UpdateFavoriteBreed>()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        detailsViewModel =
            DetailsViewModel("0", mockGetBreedById, mockUpdateFavoriteBreed, testDispatcher)
        coEvery { mockGetBreedById(any()) } returns emptyFlow()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun detailsScreenState_ReturnsChangedValues() = runBlocking {
        val firstExpectation = TestHelper.generateCatBreed()
        val secondExpectation = TestHelper.generateCatBreed(isFavorite = true)
        val testFlow = MutableStateFlow(firstExpectation)

        coEvery { mockGetBreedById(any()) } returns testFlow

        testDispatcher.scheduler.advanceUntilIdle()
        val firstResult = detailsViewModel.detailsScreenState.first()

        assertEquals(firstResult, firstExpectation)

        testFlow.emit(secondExpectation)
        testDispatcher.scheduler.advanceUntilIdle()
        val secondResult = detailsViewModel.detailsScreenState.first()

        assertEquals(secondResult, secondExpectation)
    }

    @Test
    fun onFavoriteClick_UpdatesFavoriteBreed() = runBlocking {
        val catBreed = TestHelper.generateCatBreed()
        coEvery { mockUpdateFavoriteBreed(any()) } just Runs

        detailsViewModel.onFavoriteClick(catBreed)

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = ONCE) { mockUpdateFavoriteBreed(catBreedId = catBreed.id) }
    }
}