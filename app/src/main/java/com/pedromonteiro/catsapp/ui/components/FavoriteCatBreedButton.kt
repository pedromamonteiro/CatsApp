package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed

@Composable
fun FavoriteCatBreedButton(
    catBreed: CatBreed,
    modifier: Modifier = Modifier,
    onClick: (CatBreed) -> Unit
) {
    IconButton(
        onClick = { onClick(catBreed) },
        modifier = modifier
            .size(40.dp)
            .padding(4.dp)
    ) {
        Icon(
            imageVector = if (catBreed.isFavorite)
                Icons.Filled.Star
            else
                Icons.Outlined.StarOutline,
            contentDescription = if (catBreed.isFavorite)
                stringResource(R.string.remove_favorite_cd)
            else
                stringResource(R.string.add_favorite_cd),
            tint = MaterialTheme.colorScheme.primaryContainer
        )
    }
}

@Preview
@Composable
private fun PreviewFavoriteCatBreedButton() {
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
    FavoriteCatBreedButton(catBreed = catBreed) {}
}