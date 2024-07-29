package com.pedromonteiro.catsapp.ui

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.pedromonteiro.catsapp.model.Routes

@Composable
fun RoutesNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Routes.Home.route) {
        composable(Routes.Home.route) {
            HomeScreen {
                TODO("Implement Detailed screen navigation")
            }
        }
        composable(Routes.Favorites.route) {
            TODO("Implement Favorites Screen")
        }
    }
}