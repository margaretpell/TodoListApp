package com.example.todolistapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.todolistapplication.ui.theme.TodoListApplicationTheme

class MainActivity : ComponentActivity() {
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

                    // Home Screen
                    composable("home") {
                        HomeScreen(navController, todoViewModel)
                    }

                    // Details Screen
                    composable("todo_detail/{itemId}") {
                        backStackEntry ->
                        val itemId = backStackEntry.arguments?.getString("itemId")
                        val item = todoViewModel.allTodoItems.value?.find{it.id.toString() == itemId}
                        if(item == null){
                            navController.navigate("home")
                        }
                        else{
                            Details(navController, todoViewModel, TodoItemDataModel(item.id.toString(), item.text, item.isDone))
                        }
                    }

                    composable("todo_detail/new"){
                        Details(navController, todoViewModel, null)
                    }
                }
            }
        }
    }
}