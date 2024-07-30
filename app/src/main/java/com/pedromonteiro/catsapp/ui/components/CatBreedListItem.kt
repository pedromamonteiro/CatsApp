package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.StarOutline
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed

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
            CatImage(catBreed = catBreed)
            FavoriteButton(catBreed = catBreed, onClick = onFavoriteClick)
        }
        CatBreedText(breedName = catBreed.name)
        if (showLifespan) {
            CatLifespanText(catBreed = catBreed)
        }
    }
}

@Composable
private fun BoxScope.CatImage(catBreed: CatBreed) {
    val image = catBreed.referenceImageId?.let {
        rememberAsyncImagePainter(model = catBreed.getImageUrl())
    } ?: painterResource(id = R.drawable.ic_no_image)

    Image(
        painter = image,
        contentDescription = catBreed.name,
        modifier = Modifier
            .height(100.dp)
            .fillMaxWidth()
            .align(Alignment.Center),
        contentScale = ContentScale.Crop
    )
}

@Composable
private fun BoxScope.FavoriteButton(catBreed: CatBreed, onClick: (CatBreed) -> Unit) {
    IconButton(
        onClick = { onClick(catBreed) },
        modifier = Modifier
            .size(40.dp)
            .align(Alignment.TopEnd)
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
            id = R.string.cat_lifespan_description,
            catBreed.getAverageLifespan().toString()
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
        lifeSpan = "14 - 15",
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
        lifeSpan = "14 - 15",
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