package com.pedromonteiro.catsapp.ui.components

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.model.CatBreed
import com.pedromonteiro.catsapp.model.Routes
import com.pedromonteiro.catsapp.model.Routes.Details
import com.pedromonteiro.catsapp.model.Routes.Favorites
import com.pedromonteiro.catsapp.model.Routes.Home
import com.pedromonteiro.catsapp.ui.details.DetailsViewModel
import com.pedromonteiro.catsapp.ui.home.HomeViewModel

@Composable
fun TopBar(
    currentRoute: Routes,
    onBackClick: () -> Unit,
    catBreedId: String? = null,
    homeViewModel: HomeViewModel = hiltViewModel(),
    detailsViewModel: DetailsViewModel = hiltViewModel()
) {
    var catBreed: CatBreed? = null
    val title = when (currentRoute) {
        Home -> stringResource(id = R.string.home_top_title)
        Favorites -> stringResource(id = R.string.favorites_top_title)
        Details -> {
            catBreedId?.let {
                catBreed = detailsViewModel.getCatBreed(catBreedId)
                return@let catBreed?.name
            }
        }
    } ?: stringResource(id = R.string.unknown_top_title)


    TopBar(
        currentRoute = currentRoute,
        title = title,
        catBreed = catBreed,
        onBackClick = onBackClick,
        onFavoriteClick = homeViewModel::onFavoriteClick,
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBar(
    currentRoute: Routes,
    title: String,
    catBreed: CatBreed?,
    onBackClick: () -> Unit,
    onFavoriteClick: (CatBreed) -> Unit,
) {
    Surface(shadowElevation = 10.dp) {
        TopAppBar(
            title = { Text(text = title) },
            navigationIcon = {
                if (currentRoute.hasTopBackButton()) {
                    IconButton(onClick = onBackClick) {
                        Icon(
                            imageVector = Icons.Filled.ArrowBack,
                            contentDescription = stringResource(
                                id = R.string.back_cd
                            )
                        )
                    }
                }
            },
            actions = {
                if (currentRoute.hasTopFavoriteButton()) {
                    catBreed?.let {
                        FavoriteCatBreedButton(
                            catBreed = catBreed,
                            onClick = onFavoriteClick
                        )
                    }
                }
            }
        )
    }
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewTopHomeBar() {
    TopBar(currentRoute = Home, title = "Home", null, {}, {})
}

@Preview(showSystemUi = true)
@Composable
private fun PreviewTopDetailsBar() {
    TopBar(currentRoute = Details, title = "Details", null, {}, {})
}