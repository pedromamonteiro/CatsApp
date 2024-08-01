package com.pedromonteiro.catsapp.ui.favorites

import com.pedromonteiro.catsapp.TestHelper
import com.pedromonteiro.catsapp.TestHelper.ONCE
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.domain.usecase.GetFavoriteBreeds
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
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.Arguments
import org.junit.jupiter.params.provider.Arguments.arguments
import org.junit.jupiter.params.provider.MethodSource
import java.util.stream.Stream


@OptIn(ExperimentalCoroutinesApi::class)
class FavoritesViewModelTest {
    private lateinit var favoritesViewModel: FavoritesViewModel
    private val mockGetFavoriteBreeds = mockk<GetFavoriteBreeds>()
    private val mockUpdateFavoriteBreed = mockk<UpdateFavoriteBreed>()
    private val testDispatcher = StandardTestDispatcher()

    @BeforeEach
    fun setUp() {
        Dispatchers.setMain(testDispatcher)
        favoritesViewModel =
            FavoritesViewModel(mockGetFavoriteBreeds, mockUpdateFavoriteBreed, testDispatcher)
        coEvery { mockGetFavoriteBreeds() } returns emptyFlow()
    }

    @AfterEach
    fun tearDown() {
        Dispatchers.resetMain()
    }

    @Test
    fun favoritesScreenState_ReturnsChangedValues() = runBlocking {
        val firstExpectation =
            FavoriteScreenState(TestHelper.generateCatBreeds(10, isFavorite = true))
        val secondExpectation =
            FavoriteScreenState(TestHelper.generateCatBreeds(9, isFavorite = true))
        val testFlow = MutableStateFlow(firstExpectation.favoriteCatBreeds)

        coEvery { mockGetFavoriteBreeds() } returns testFlow

        testDispatcher.scheduler.advanceUntilIdle()
        val firstResult = favoritesViewModel.favoriteScreenState.first()

        assertEquals(firstResult, firstExpectation)

        testFlow.emit(secondExpectation.favoriteCatBreeds)
        testDispatcher.scheduler.advanceUntilIdle()
        val secondResult = favoritesViewModel.favoriteScreenState.first()

        assertEquals(secondResult, secondExpectation)
    }

    @ParameterizedTest
    @MethodSource("provideFavoriteAndCancelPopupClicksTestCases")
    fun onFavoriteCancelPopupClicks_UpdatesFavoriteScreenState_DoNotAffectFavoriteCatBreedsLists(
        showRemoveFavoritePopup: Boolean,
        favoriteToBeRemoved: CatBreed?
    ) = runBlocking {
        val expected = FavoriteScreenState(
            favoriteCatBreeds = TestHelper.generateCatBreeds(10, isFavorite = true),
            showRemoveFavoritePopup = showRemoveFavoritePopup,
            favoriteToBeRemoved = favoriteToBeRemoved
        )
        coEvery { mockGetFavoriteBreeds() } returns flowOf(expected.favoriteCatBreeds)

        testDispatcher.scheduler.advanceUntilIdle()
        if (showRemoveFavoritePopup) {
            favoritesViewModel.onFavoriteClick(expected.favoriteToBeRemoved!!)
        } else {
            favoritesViewModel.onPopupCancel()
        }
        val result = favoritesViewModel.favoriteScreenState.first()

        assertEquals(result, expected)
    }

    @Test
    fun onPopupConfirm_UpdatesFavoriteBreed_UpdatesFavoriteScreenState() = runBlocking {
        val expected =
            FavoriteScreenState(showRemoveFavoritePopup = false, favoriteToBeRemoved = null)
        val breed = TestHelper.generateCatBreed(isFavorite = true)
        coEvery { mockUpdateFavoriteBreed(any()) } just Runs

        favoritesViewModel.onFavoriteClick(breed)
        favoritesViewModel.onPopupConfirm()

        testDispatcher.scheduler.advanceUntilIdle()

        val result = favoritesViewModel.favoriteScreenState.first()

        coVerify(exactly = ONCE) { mockUpdateFavoriteBreed(breed.id) }
        assertEquals(result, expected)
    }

    companion object {
        @JvmStatic
        private fun provideFavoriteAndCancelPopupClicksTestCases(): Stream<Arguments?>? {
            // (showRemoveFavoritePopup, favoriteToBeRemoved)
            return Stream.of(
                arguments(true, TestHelper.generateCatBreed(isFavorite = true)),
                arguments(false, null)
            )
        }
    }
}