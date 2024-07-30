package com.pedromonteiro.catsapp.ui.favorites

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromonteiro.catsapp.domain.usecase.GetFavoriteBreeds
import com.pedromonteiro.catsapp.domain.usecase.UpdateFavoriteBreed
import com.pedromonteiro.catsapp.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class FavoritesViewModel @Inject constructor(
    private val getFavoriteBreeds: GetFavoriteBreeds,
    private val updateFavoriteBreed: UpdateFavoriteBreed,
) : ViewModel() {
    private val _favoriteScreenState = MutableStateFlow(FavoriteScreenState())
    val favoriteScreenState = _favoriteScreenState.asStateFlow()

    init {
        subscribeToFavoriteBreeds()
    }

    fun onFavoriteClick(catBreed: CatBreed) =
        updateFavoriteToBeRemovedState(true, catBreed)

    fun onPopupCancel() =
        updateFavoriteToBeRemovedState(false, null)

    fun onPopupConfirm() =
        viewModelScope.launch {
            _favoriteScreenState.value.favoriteToBeRemoved?.id?.let { catBreedId ->
                updateFavoriteBreed(catBreedId)
            }
            updateFavoriteToBeRemovedState(false, null)
        }

    private fun updateFavoriteToBeRemovedState(showPopup: Boolean, favoriteToBeRemoved: CatBreed?) {
        _favoriteScreenState.value = _favoriteScreenState.value.copy(
            showRemoveFavoritePopup = showPopup,
            favoriteToBeRemoved = favoriteToBeRemoved
        )
    }

    private fun subscribeToFavoriteBreeds() {
        viewModelScope.launch {
            getFavoriteBreeds().collect { favoriteCatBreeds ->
                _favoriteScreenState.value =
                    _favoriteScreenState.value.copy(favoriteCatBreeds = favoriteCatBreeds)
            }
        }
    }
}