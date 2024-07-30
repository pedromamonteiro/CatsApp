package com.pedromonteiro.catsapp.ui.details

import androidx.lifecycle.ViewModel
import com.pedromonteiro.catsapp.domain.usecase.GetBreedById
import com.pedromonteiro.catsapp.model.CatBreed
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val getBreedById: GetBreedById
) : ViewModel() {

    fun getCatBreed(catBreedId: String): CatBreed? {
        return runBlocking(context = Dispatchers.IO) {
            return@runBlocking getBreedById(catBreedId).firstOrNull()
        }
    }
}