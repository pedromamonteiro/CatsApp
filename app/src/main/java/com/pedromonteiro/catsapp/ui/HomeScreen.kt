package com.pedromonteiro.catsapp.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.CatBreedListItem
import com.pedromonteiro.catsapp.ui.viewmodel.CatViewModel

@Composable
fun HomeScreen(viewModel: CatViewModel = hiltViewModel(), onCatClick: (CatBreed) -> Unit) {
    val catBreeds by viewModel.breedsFlow.collectAsState()
    // TODO When doing the search feature make this a mutable state
    val search = ""

    HomeScreen(
        searchString = "",
        catBreeds = catBreeds,
        onSearchChanged = { TODO("Make viewmodel do the search and update catBreeds") },
        onClick = onCatClick,
        onFavoriteClick = { TODO("Use viewmodel to mark the cat breed as favorite") }
    )
}

@Composable
private fun HomeScreen(
    searchString: String = "",
    catBreeds: List<CatBreed>,
    onSearchChanged: (String) -> Unit,
    onClick: (CatBreed) -> Unit,
    onFavoriteClick: (CatBreed) -> Unit
) {
    Column {
        OutlinedTextField(
            value = searchString,
            onValueChange = onSearchChanged,
            placeholder = { Text(text = stringResource(id = R.string.search_by_breed_ph)) },
            modifier = Modifier
                .padding(horizontal = 16.dp, vertical = 8.dp)
                .fillMaxWidth()
        )
        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp),
            modifier = Modifier.fillMaxSize()
        ) {
            items(catBreeds) { catBreed ->
                CatBreedListItem(
                    catBreed = catBreed,
                    onClick = onClick,
                    onFavoriteClick = onFavoriteClick
                )
            }
        }
    }
}

@Preview
@Composable
private fun PreviewHomeScreen() {
    val breed = CatBreed(
        id = "abys",
        name = "Abyssiniaan",
        referenceImageId = "0XYvRd7oD",
        lifeSpan = "14 - 15",
        origin = "Egypt",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        isFavorite = false
    )
    val catBreeds = listOf(breed, breed, breed, breed, breed, breed, breed)

    HomeScreen("", catBreeds, {}, {}, {})
}