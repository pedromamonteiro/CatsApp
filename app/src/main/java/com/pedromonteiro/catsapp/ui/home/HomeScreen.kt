package com.pedromonteiro.catsapp.ui.home

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
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.CatBreedListItem

@Composable
fun HomeScreen(viewModel: HomeViewModel = hiltViewModel(), onCatClick: (CatBreed) -> Unit) {
    val homeScreenState by viewModel.homeScreenState.collectAsStateWithLifecycle()

    HomeScreen(
        homeScreenState = homeScreenState,
        onSearchChanged = viewModel::onSearchChanged,
        onClick = onCatClick,
        onFavoriteClick = viewModel::onFavoriteClick
    )
}

@Composable
private fun HomeScreen(
    homeScreenState: HomeScreenState,
    onSearchChanged: (String) -> Unit,
    onClick: (CatBreed) -> Unit,
    onFavoriteClick: (CatBreed) -> Unit
) {
    Column {
        OutlinedTextField(
            value = homeScreenState.searchString,
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
            items(homeScreenState.catBreeds) { catBreed ->
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

    HomeScreen(HomeScreenState(catBreeds), {}, {}, {})
}