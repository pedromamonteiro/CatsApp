package com.pedromonteiro.catsapp.ui.details

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromonteiro.catsapp.di.IoDispatcher
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.domain.usecase.GetBreedById
import com.pedromonteiro.catsapp.domain.usecase.UpdateFavoriteBreed
import com.pedromonteiro.catsapp.ui.details.DetailsViewModel.DetailsViewModelFactory
import dagger.assisted.Assisted
import dagger.assisted.AssistedFactory
import dagger.assisted.AssistedInject
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

@HiltViewModel(assistedFactory = DetailsViewModelFactory::class)
class DetailsViewModel @AssistedInject constructor(
    @Assisted private val catBreedId: String,
    private val getBreedById: GetBreedById,
    private val updateFavoriteBreed: UpdateFavoriteBreed,
    @IoDispatcher private val ioDispatcher: CoroutineDispatcher
) : ViewModel() {
    @AssistedFactory
    interface DetailsViewModelFactory {
        fun create(catBreedId: String): DetailsViewModel
    }

    private val _detailsScreenState = MutableStateFlow<CatBreed?>(null)
    val detailsScreenState = _detailsScreenState.asStateFlow()

    init {
        viewModelScope.launch(ioDispatcher) {
            getBreedById(catBreedId).collect { catBreed ->
                _detailsScreenState.value = catBreed
            }
        }
    }

    fun onFavoriteClick(catBreed: CatBreed) {
        viewModelScope.launch(ioDispatcher) {
            updateFavoriteBreed(catBreedId = catBreed.id)
        }
    }
}