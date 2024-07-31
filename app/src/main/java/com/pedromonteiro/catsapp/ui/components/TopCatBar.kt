package com.pedromonteiro.catsapp.ui.components

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pedromonteiro.catsapp.R
import com.pedromonteiro.catsapp.domain.model.Routes
import com.pedromonteiro.catsapp.domain.model.Routes.Favorites
import com.pedromonteiro.catsapp.domain.model.Routes.Home

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopCatBar(currentRoute: Routes) {
    val title = getTopBarTitle(currentRoute = currentRoute)
    Surface(shadowElevation = 10.dp) {
        TopAppBar(
            title = { Text(text = title) }
        )
    }
}

@Composable
private fun getTopBarTitle(currentRoute: Routes): String =
    when (currentRoute) {
        Home -> stringResource(id = R.string.home_top_title)
        Favorites -> stringResource(id = R.string.favorites_top_title)
        else -> stringResource(id = R.string.unknown_top_title)
    }

@Preview(showSystemUi = true)
@Composable
private fun PreviewTopCatBar() {
    TopCatBar(currentRoute = Home)
}