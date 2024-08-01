package com.pedromonteiro.catsapp.ui.favorites

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.CatBreedListItem

@Composable
fun FavoritesScreen(viewModel: FavoritesViewModel = hiltViewModel()) {
    val favoriteScreenState by viewModel.favoriteScreenState.collectAsStateWithLifecycle()

    FavoritesScreen(
        favoriteScreenState = favoriteScreenState,
        onFavoriteClick = viewModel::onFavoriteClick,
        onPopupCancel = viewModel::onPopupCancel,
        onPopupConfirm = viewModel::onPopupConfirm
    )
}

@Composable
private fun FavoritesScreen(
    favoriteScreenState: FavoriteScreenState,
    onFavoriteClick: (CatBreed) -> Unit,
    onPopupCancel: () -> Unit,
    onPopupConfirm: () -> Unit
) {
    if (favoriteScreenState.showRemoveFavoritePopup) {
        RemoveFavoriteDialog(onCancel = onPopupCancel, onConfirm = onPopupConfirm)
    }

    val favoriteCatBreeds = favoriteScreenState.favoriteCatBreeds
    if (favoriteCatBreeds.isEmpty()) {
        NoFavoritesMessage()
    } else {
        FavoriteBreedsListGrid(
            favoriteCatBreeds = favoriteCatBreeds,
            onClick = onFavoriteClick
        )
    }
}

@Composable
private fun NoFavoritesMessage() {
    Text(
        text = stringResource(id = R.string.no_favorites_desc),
        style = MaterialTheme.typography.bodyLarge,
        textAlign = TextAlign.Center,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    )
}

@Composable
private fun FavoriteBreedsListGrid(favoriteCatBreeds: List<CatBreed>, onClick: (CatBreed) -> Unit) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = Modifier.fillMaxSize()
    ) {
        items(favoriteCatBreeds) { favoriteBreed ->
            CatBreedListItem(
                catBreed = favoriteBreed,
                showLifespan = true,
                onClick = onClick,
                onFavoriteClick = onClick
            )
        }
    }
}

@Composable
private fun RemoveFavoriteDialog(onCancel: () -> Unit, onConfirm: () -> Unit) {
    AlertDialog(
        title = {
            Text(text = stringResource(id = R.string.popup_title_desc))
        },
        onDismissRequest = onCancel,
        dismissButton = {
            TextButton(onClick = { onCancel() }) {
                Text(text = stringResource(id = R.string.popup_dismiss_desc))
            }
        },
        confirmButton = {
            TextButton(onClick = { onConfirm() }) {
                Text(text = stringResource(id = R.string.popup_confirm_desc))
            }
        }
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewFavoritesScreen() {
    val catBreed = CatBreed(
        id = "abys",
        name = "Abyssiniaan",
        referenceImageId = "0XYvRd7oD",
        lifeSpan = "14 - 15",
        origin = "Egypt",
        temperament = "Active, Energetic, Independent, Intelligent, Gentle",
        description = "The Abyssinian is easy to care for, and a joy to have in your home. They’re affectionate cats and love both people and other animals.",
        isFavorite = true
    )
    val catBreeds =
        listOf(catBreed, catBreed, catBreed, catBreed, catBreed, catBreed, catBreed, catBreed)

    FavoritesScreen(
        favoriteScreenState = FavoriteScreenState(catBreeds),
        onFavoriteClick = {},
        onPopupCancel = {},
        onPopupConfirm = {}
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewNoFavoritesScreen() {
    FavoritesScreen(
        favoriteScreenState = FavoriteScreenState(),
        onFavoriteClick = {},
        onPopupCancel = {},
        onPopupConfirm = {}
    )
}