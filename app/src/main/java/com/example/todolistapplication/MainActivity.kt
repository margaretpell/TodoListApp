package com.example.todolistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.todolistapplication.details.DetailsScreen
import com.example.todolistapplication.details.DetailsScreenViewModel
import com.example.todolistapplication.home.HomeScreen
import com.example.todolistapplication.home.HomeScreenViewModel
import com.example.todolistapplication.ui.theme.TodoListApplicationTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TodoListApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = NavRoutes.HOME
                ) {
                    // Home Screen
                    composable(NavRoutes.HOME) {
                        val homeScreenViewModel: HomeScreenViewModel by viewModels()
                        HomeScreen(navController, homeScreenViewModel)
                    }

                    // Details Screen
                    composable(
                        NavRoutes.TODO_DETAILS,
                        arguments = listOf(navArgument("itemId") { type = NavType.StringType })
                    ) { backStackEntry ->
                        val viewModel: DetailsScreenViewModel by viewModels()
                        val itemId = backStackEntry.arguments?.getString("itemId")

                        DetailsScreen(
                            navController,
                            viewModel,
                            itemId = itemId
                        )

                    }

                    composable(NavRoutes.TODO_DETAILS_NEW) {
                        val viewModel: DetailsScreenViewModel by viewModels()
                        DetailsScreen(navController, viewModel, itemId = null)
                    }
                }
            }
        }
    }
}