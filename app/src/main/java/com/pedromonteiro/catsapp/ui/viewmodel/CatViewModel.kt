package com.pedromonteiro.catsapp.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pedromonteiro.catsapp.domain.usecase.GetBreeds
import com.pedromonteiro.catsapp.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CatViewModel @Inject constructor(
    private val getBreeds: GetBreeds
) : ViewModel() {
    private val _breeds = MutableStateFlow<List<CatBreed>>(emptyList())
    val breedsFlow = _breeds.asStateFlow()

    init {
        populateBreeds()
    }

    private fun populateBreeds() {
        viewModelScope.launch {
            _breeds.value = getBreeds()
        }
    }

}