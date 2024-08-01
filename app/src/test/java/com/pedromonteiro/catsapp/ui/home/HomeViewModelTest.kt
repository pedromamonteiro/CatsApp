package com.pedromonteiro.catsapp.ui.home

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.TestHelper.ONCE
import com.pedromonteiro.catsapp.domain.usecase.GetBreeds
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
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test

@OptIn(ExperimentalCoroutinesApi::class)
class HomeViewModelTest {
    private lateinit var homeViewModel: HomeViewModel
    private val mockGetBreeds = mockk<GetBreeds>()
    private val mockUpdateFavoriteBreed = mockk<UpdateFavoriteBreed>()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        homeViewModel = HomeViewModel(mockGetBreeds, mockUpdateFavoriteBreed, testDispatcher)
        coEvery { mockGetBreeds() } returns emptyFlow()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun homeScreenState_WithoutSearch_ReturnsSeveralEmittedBreeds() = runBlocking {
        val firstExpectation = HomeScreenState(
            catBreeds = TestHelper.generateCatBreeds(10)
        )
        val secondExpectation = HomeScreenState(
            catBreeds = TestHelper.generateCatBreeds(5)
        )
        val testFlow = MutableStateFlow(firstExpectation.catBreeds)

        coEvery { mockGetBreeds() } returns testFlow

        testDispatcher.scheduler.advanceUntilIdle()
        val firstResult = homeViewModel.homeScreenState.first()

        assertEquals(firstResult, firstExpectation)

        testFlow.emit(secondExpectation.catBreeds)
        testDispatcher.scheduler.advanceUntilIdle()
        val secondResult = homeViewModel.homeScreenState.first()

        assertEquals(secondResult, secondExpectation)
    }

    @Test
    fun homeScreenState_WithSearch_ReturnsExpectedFilteredBreeds() = runBlocking {
        val catBreeds = TestHelper.generateCatBreeds(15)
        val expected = HomeScreenState(
            catBreeds = listOf(
                catBreeds[1],
                catBreeds[10],
                catBreeds[11],
                catBreeds[12],
                catBreeds[13],
                catBreeds[14]
            ),
            searchString = "1"
        )
        coEvery { mockGetBreeds() } returns flowOf(catBreeds)

        homeViewModel.onSearchChanged(expected.searchString)

        testDispatcher.scheduler.advanceUntilIdle()
        val result = homeViewModel.homeScreenState.first()

        assertEquals(result, expected)
    }

    @Test
    fun onFavoriteClick_UpdatesFavoriteBreed() = runBlocking {
        val catBreed = TestHelper.generateCatBreed()
        coEvery { mockUpdateFavoriteBreed(any()) } just Runs

        homeViewModel.onFavoriteClick(catBreed)

        testDispatcher.scheduler.advanceUntilIdle()

        coVerify(exactly = ONCE) { mockUpdateFavoriteBreed(catBreedId = catBreed.id) }
    }
}