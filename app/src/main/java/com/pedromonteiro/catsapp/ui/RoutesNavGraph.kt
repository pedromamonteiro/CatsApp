package com.pedromonteiro.catsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pedromonteiro.catsapp.model.Routes
import com.pedromonteiro.catsapp.ui.details.DetailsScreen
import com.pedromonteiro.catsapp.ui.favorites.FavoritesScreen
import com.pedromonteiro.catsapp.ui.home.HomeScreen

@Composable
fun RoutesNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen(
                onCatClick = { catBreed ->
                    navController.navigate(
                        Routes.Details.route.replace(
                            "{catBreedId}",
                            catBreed.id
                        )
                    )
                }
            )
        }
        composable(Routes.Favorites.route) { FavoritesScreen() }
        composable(Routes.Details.route) { navBackStackEntry ->
            navBackStackEntry.arguments?.getString("catBreedId")?.let { catBreedId ->
                DetailsScreen(
                    catBreedId = catBreedId,
                    onBackClick = { navController.navigateUp() }
                )
            }
        }
    }
}