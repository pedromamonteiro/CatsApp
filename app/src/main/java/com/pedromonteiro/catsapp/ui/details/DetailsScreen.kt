package com.pedromonteiro.catsapp.ui.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.data.entity.CatBreedDTO
import com.pedromonteiro.catsapp.domain.model.CatBreed
import com.pedromonteiro.catsapp.ui.components.CatImage
import com.pedromonteiro.catsapp.ui.components.FavoriteCatBreedButton
import com.pedromonteiro.catsapp.ui.details.DetailsViewModel.DetailsViewModelFactory

@Composable
fun DetailsScreen(catBreedId: String, onBackClick: () -> Unit) {
    val viewModel: DetailsViewModel =
        hiltViewModel { factory: DetailsViewModelFactory -> factory.create(catBreedId) }
    viewModel.detailsScreenState.collectAsStateWithLifecycle().value?.let { catBreed ->
        DetailsScreen(
            catBreed = catBreed,
            onBackClick = onBackClick,
            onFavoriteClick = viewModel::onFavoriteClick
        )
    }
}

@Composable
private fun DetailsScreen(
    catBreed: CatBreed,
    onBackClick: () -> Unit,
    onFavoriteClick: (CatBreed) -> Unit
) {
    Scaffold(
        topBar = {
            DetailsTopBar(
                catBreed = catBreed,
                onBackClick = onBackClick,
                onFavoriteClick = onFavoriteClick
            )
        },
        content = { padding ->
            Column(
                modifier = Modifier
                    .fillMaxSize()
                    .verticalScroll(rememberScrollState())
                    .padding(padding)
                    .padding(16.dp)
            ) {
                CatImage(
                    catBreed = catBreed,
                    modifier = Modifier
                        .height(300.dp)
                        .fillMaxWidth()
                        .padding(horizontal = 36.dp)
                        .align(Alignment.CenterHorizontally)
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = catBreed.origin,
                    style = MaterialTheme.typography.bodyLarge
                )
                Spacer(modifier = Modifier.height(4.dp))
                Text(
                    text = catBreed.temperament,
                    style = MaterialTheme.typography.bodySmall
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = catBreed.description,
                    style = MaterialTheme.typography.bodyMedium
                )
            }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun DetailsTopBar(
    catBreed: CatBreed,
    onBackClick: () -> Unit,
    onFavoriteClick: (CatBreed) -> Unit,
) {
    Surface(shadowElevation = 10.dp) {
        TopAppBar(
            title = { Text(text = catBreed.name) },
            navigationIcon = {
                IconButton(onClick = onBackClick) {
                    Icon(
                        imageVector = Icons.Filled.ArrowBack,
                        contentDescription = stringResource(
                            id = R.string.back_cd
                        )
                    )
                }
            },
            actions = {
                FavoriteCatBreedButton(
                    catBreed = catBreed,
                    onClick = onFavoriteClick
                )
            }
        )
    }
}


@Preview
@Composable
private fun PreviewDetailsScreen() {
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
    DetailsScreen(catBreed, {}, {})
}