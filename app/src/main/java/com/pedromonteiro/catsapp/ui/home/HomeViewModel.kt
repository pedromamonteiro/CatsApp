package com.pedromonteiro.catsapp.ui.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromonteiro.catsapp.domain.usecase.GetBreeds
import com.pedromonteiro.catsapp.domain.usecase.UpdateFavoriteBreed
import com.pedromonteiro.catsapp.model.CatBreed
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
    val homeScreenState = _homeScreenState.asStateFlow()

    init {
        subscribeToBreeds()
    }

    fun onFavoriteClick(catBreed: CatBreed) {
        viewModelScope.launch {
            updateFavoriteBreed(catBreedId = catBreed.id)
        }
    }

    private fun subscribeToBreeds() {
        viewModelScope.launch {
            getBreeds().collect { catBreeds ->
                _homeScreenState.value = _homeScreenState.value.copy(catBreeds = catBreeds)
            }
        }
    }
}