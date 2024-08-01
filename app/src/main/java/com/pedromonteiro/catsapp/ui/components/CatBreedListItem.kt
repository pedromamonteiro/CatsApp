package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.domain.model.CatBreed

@Composable
fun CatBreedListItem(
    catBreed: CatBreed,
    showLifespan: Boolean = false,
    onClick: (CatBreed) -> Unit,
    onFavoriteClick: (CatBreed) -> Unit
) {
    Column(modifier = Modifier
        .width(100.dp)
        .clickable { onClick(catBreed) }
    ) {
        Box {
            CatImage(
                catBreed = catBreed,
                modifier = Modifier
                    .height(100.dp)
                    .fillMaxWidth()
                    .align(Alignment.Center)
            )
            FavoriteCatBreedButton(
                catBreed = catBreed,
                modifier = Modifier.align(Alignment.TopEnd),
                onClick = onFavoriteClick
            )
        }
        CatBreedText(breedName = catBreed.name)
        if (showLifespan) {
            CatLifespanText(catBreed = catBreed)
        }
    }
}

@Composable
private fun CatBreedText(breedName: String) {
    Text(
        text = breedName,
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodyMedium,
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .fillMaxWidth()
    )
}

@Composable
private fun CatLifespanText(catBreed: CatBreed) {
    Text(
        text = stringResource(
            id = R.string.cat_lifespan_desc,
            catBreed.averageLifeSpan.toString()
        ),
        textAlign = TextAlign.Center,
        style = MaterialTheme.typography.bodySmall,
        modifier = Modifier
            .padding(horizontal = 4.dp, vertical = 2.dp)
            .fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
private fun PreviewCatBreedListItem() {
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
    CatBreedListItem(catBreed = catBreed, onClick = {}, onFavoriteClick = {})
}

@Preview(showBackground = true)
@Composable
private fun PreviewCatBreedListItemWithAverageLifespan() {
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
    CatBreedListItem(
        catBreed = catBreed,
        showLifespan = true,
        onClick = {},
        onFavoriteClick = {})
}