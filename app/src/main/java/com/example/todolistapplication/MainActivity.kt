package com.example.todolistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapplication.details.DetailsScreen
import com.example.todolistapplication.home.HomeScreen
import com.example.todolistapplication.home.HomeScreenViewModel
import com.example.todolistapplication.ui.theme.TodoListApplicationTheme

class MainActivity : ComponentActivity() {
    // TODO: Remove this.
    private val todoViewModel: TodoViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()

            TodoListApplicationTheme {
                NavHost(
                    navController = navController,
                    startDestination = "home"
                ) {

                    // TODO: Can we extract all the routes to a separate file and maintain constants there?
                    // Home Screen
                    composable("home") {
                        val homeScreenViewModel: HomeScreenViewModel by viewModels()
                        HomeScreen(navController, homeScreenViewModel)
                    }

                    // Details Screen
                    composable("todo_detail/{itemId}") { backStackEntry ->
                        val itemId = backStackEntry.arguments?.getString("itemId")
                        val item =
                            todoViewModel.allTodoItems.value?.find { it.id.toString() == itemId }
                        if (item == null) {
                            navController.navigate("home")
                        } else {
                            DetailsScreen(
                                navController,
                                todoViewModel,
                                TodoItemDataModel(item.id.toString(), item.text, item.isDone)
                            )
                        }
                    }

                    composable("todo_detail/new") {
                        DetailsScreen(navController, todoViewModel, null)
                    }
                }
            }
        }
    }
}