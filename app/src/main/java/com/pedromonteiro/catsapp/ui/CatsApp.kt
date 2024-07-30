package com.pedromonteiro.catsapp.ui

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.rememberNavController
import com.pedromonteiro.catsapp.model.Routes
import com.pedromonteiro.catsapp.ui.components.BottomNavigation
import com.pedromonteiro.catsapp.ui.theme.CatsAppTheme

@Composable
fun CatsApp() {
    // Navigation
    val navController = rememberNavController()
    var currentRoute by rememberSaveable { mutableStateOf(Routes.Home) }

    navController.addOnDestinationChangedListener { _, destination, _ ->
        currentRoute = when (destination.route) {
            Routes.Home.route -> Routes.Home
            Routes.Favorites.route -> Routes.Favorites
            else -> throw IllegalArgumentException("Unknown destination")
        }
    }

    CatsAppTheme {
        Scaffold(
            //TODO("Create top app bar")
            topBar = { },
            content = { contentPadding ->
                Surface(modifier = Modifier.padding(contentPadding)) {
                    RoutesNavGraph(navController = navController)
                }
            },
            bottomBar = {
                BottomNavigation(currentRoute = currentRoute) { route ->
                    if (currentRoute.route != route.route) {
                        navController.navigate(route.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                }
            }
        )
    }
}