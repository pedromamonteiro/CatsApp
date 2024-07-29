package com.pedromonteiro.catsapp.ui.components

import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import com.pedromonteiro.catsapp.model.Routes

@Composable
fun BottomNavigation(
    currentRoute: Routes,
    onClick: (Routes) -> Unit
) {
    BottomAppBar {
        Routes.BOTTOM_NAVIGATION_ROUTES.forEach { route ->
            NavigationBarItem(
                selected = currentRoute == route,
                onClick = { onClick(route) },
                icon = {
                    Icon(
                        imageVector = route.getBottomNavigationIcon(currentRoute),
                        contentDescription = stringResource(
                            id = route.getBottomNavigationContentDescription(
                                currentRoute
                            )
                        )
                    )
                }
            )
        }
    }
}

@Preview
@Composable
private fun PreviewBottomNavigationHome() {
    BottomNavigation(currentRoute = Routes.Home) {}
}

@Preview
@Composable
private fun PreviewBottomNavigationFavorites() {
    BottomNavigation(currentRoute = Routes.Favorites) {}
}