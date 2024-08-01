package com.pedromonteiro.catsapp.ui.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.InfoMessage
import com.pedromonteiro.catsapp.ui.components.VerticalCatBreedGrid

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
        if (homeScreenState.localDataEmpty) {
            InfoMessage(text = stringResource(id = R.string.no_data_desc))
        } else {
            CatBreedSearchBar(
                value = homeScreenState.searchString,
                onValueChange = onSearchChanged,
                modifier = Modifier
                    .padding(horizontal = 16.dp, vertical = 8.dp)
                    .fillMaxWidth()
            )
            VerticalCatBreedGrid(
                catBreeds = homeScreenState.catBreeds,
                onClick = onClick,
                onFavoriteClick = onFavoriteClick,
                modifier = Modifier.fillMaxSize()
            )
        }
    }
}

@Composable
private fun CatBreedSearchBar(
    value: String,
    onValueChange: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    OutlinedTextField(
        value = value,
        onValueChange = onValueChange,
        placeholder = { Text(text = stringResource(id = R.string.search_by_breed_ph)) },
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewHomeScreen() {
    val catBreed = CatBreed(
        id = "abys",
        name = "Abyssiniaan",
        referenceImageId = "0XYvRd7oD",
        referenceImageUrl = "",
        lifeSpan = "14 - 15",
        averageLifeSpan = 14,
        origin = "Egypt",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        isFavorite = false
    )
    val catBreeds = listOf(catBreed, catBreed, catBreed, catBreed, catBreed, catBreed, catBreed)

    HomeScreen(HomeScreenState(catBreeds), {}, {}, {})
}