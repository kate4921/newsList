package com.example.practise2023.presentation.navgraph

import androidx.activity.compose.BackHandler
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.practise2023.domain.model.Article
import com.example.practise2023.presentation.details.DetailsScreen
import com.example.practise2023.presentation.details.DetailsViewModel
import com.example.practise2023.presentation.home.HomeScreen

@Composable
fun NavGraph() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Route.HomeScreen.route) {
        composable(route = Route.HomeScreen.route) {
            HomeScreen(
//                        navigateToSearch = {},
                navigateToDetails = { article ->
                    navigateToDetails(
                        navController = navController,
                        article = article
                    )
                }
            )
        }
        composable(route = Route.DetailsScreen.route) {
            val viewModel: DetailsViewModel = hiltViewModel()
            navController.previousBackStackEntry?.savedStateHandle?.get<Article?>("article")
                ?.let { article ->
                    DetailsScreen(
                        article = article,
                        event = viewModel::onEvent,
                        navigateUp = { navController.navigateUp() },
                        sideEffect = viewModel.sideEffect
                    )
                }

        }
    }
}

@Composable
fun OnBackClickStateSaver(navController: NavController) {
    BackHandler(true) {
        navigateToTab(
            navController = navController,
            route = Route.HomeScreen.route
        )
    }
}

private fun navigateToTab(navController: NavController, route: String) {
    navController.navigate(route) {
        navController.graph.startDestinationRoute?.let { screen_route ->
            popUpTo(screen_route) {
                saveState = true
            }
        }
        launchSingleTop = true
        restoreState = true
    }
}

private fun navigateToDetails(navController: NavController, article: Article) {
    navController.currentBackStackEntry?.savedStateHandle?.set("article", article)
    navController.navigate(
        route = Route.DetailsScreen.route
    )
}