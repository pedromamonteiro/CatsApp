package com.pedromonteiro.catsapp.ui.components

import androidx.compose.foundation.Image
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import coil.compose.rememberAsyncImagePainter
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed

@Composable
fun CatImage(catBreed: CatBreed, modifier: Modifier = Modifier) {
    Image(
        painter = rememberAsyncImagePainter(
            model = catBreed.getImageUrl(),
            error = painterResource(id = R.drawable.ic_no_image)
        ),
        contentDescription = catBreed.name,
        modifier = modifier,
        contentScale = ContentScale.Crop
    )
}