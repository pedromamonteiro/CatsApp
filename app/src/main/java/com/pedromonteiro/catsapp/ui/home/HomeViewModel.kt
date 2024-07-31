package com.pedromonteiro.catsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromonteiro.catsapp.domain.usecase.GetBreeds
import com.pedromonteiro.catsapp.domain.usecase.UpdateFavoriteBreed
import com.pedromonteiro.catsapp.domain.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getBreeds: GetBreeds,
    private val updateFavoriteBreed: UpdateFavoriteBreed,
) : ViewModel() {
    private val _homeScreenState = MutableStateFlow(HomeScreenState())
    private var allBreeds: List<CatBreed> = emptyList()
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        subscribeToBreeds()
    }

    fun onFavoriteClick(catBreed: CatBreed) {
        viewModelScope.launch {
            updateFavoriteBreed(catBreedId = catBreed.id)
        }
    }

    fun onSearchChanged(searchString: String) {
        _homeScreenState.value = _homeScreenState.value.copy(searchString = searchString)
        performFilter()
    }

    private fun subscribeToBreeds() {
        viewModelScope.launch {
            getBreeds().collect { catBreeds ->
                allBreeds = catBreeds
                performFilter()
            }
        }
    }

    private fun performFilter() {
        val searchString = _homeScreenState.value.searchString
        val filteredBreeds = if (searchString.isEmpty()) {
            allBreeds
        } else {
            allBreeds.filter { it.name.contains(searchString, ignoreCase = true) }
        }

        _homeScreenState.value = _homeScreenState.value.copy(searchString = searchString, catBreeds = filteredBreeds)
    }
}