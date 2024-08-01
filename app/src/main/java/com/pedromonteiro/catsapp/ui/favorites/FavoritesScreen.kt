package com.pedromonteiro.catsapp.ui.favorites

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.InfoMessage
import com.pedromonteiro.catsapp.ui.components.VerticalCatBreedGrid

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
        InfoMessage(
            text = stringResource(id = R.string.no_favorites_desc)
        )
    } else {
        VerticalCatBreedGrid(
            catBreeds = favoriteCatBreeds,
            onClick = onFavoriteClick,
            onFavoriteClick = onFavoriteClick,
            modifier = Modifier.fillMaxSize(),
            showLifespan = true
        )
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
        referenceImageUrl = "",
        lifeSpan = "14 - 15",
        averageLifeSpan = 14,
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