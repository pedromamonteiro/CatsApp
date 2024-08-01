package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedromonteiro.catsapp.domain.model.CatBreed

@Composable
fun VerticalCatBreedGrid(
    catBreeds: List<CatBreed>,
    onClick: (CatBreed) -> Unit,
    onFavoriteClick: (CatBreed) -> Unit,
    modifier: Modifier = Modifier,
    showLifespan: Boolean = false,
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(8.dp),
        modifier = modifier
    ) {
        items(catBreeds) { catBreed ->
            CatBreedListItem(
                catBreed = catBreed,
                showLifespan = showLifespan,
                onClick = onClick,
                onFavoriteClick = onFavoriteClick
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalCatBreedGrid() {
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
    VerticalCatBreedGrid(catBreeds = catBreeds, onClick = {}, onFavoriteClick = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewVerticalCatBreedGridWithLifespan() {
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
    VerticalCatBreedGrid(
        catBreeds = catBreeds,
        onClick = {},
        onFavoriteClick = {},
        showLifespan = true
    )
}