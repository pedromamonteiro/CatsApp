package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.Star
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
        Text(
            text = catBreed.name,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .padding(4.dp)
                .fillMaxWidth()
        )
    }
}

@Composable
private fun CatImage(catBreed: CatBreed) {
    val image = catBreed.referenceImageId?.let {
        rememberAsyncImagePainter(model = catBreed.getImageUrl())
    } ?: painterResource(id = R.drawable.ic_no_image)

    Image(
        painter = image,
        contentDescription = catBreed.name,
        modifier = Modifier
            .size(100.dp),
        contentScale = ContentScale.FillHeight
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
            imageVector = if (catBreed.isFavorite) Icons.Filled.Star else Icons.Outlined.Star,
            contentDescription = if (catBreed.isFavorite)
                stringResource(R.string.remove_favorite_cd)
            else
                stringResource(R.string.add_favorite_cd),
            tint = MaterialTheme.colorScheme.primary
        )
    }
}

@Preview
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