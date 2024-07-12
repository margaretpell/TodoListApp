package com.example.todolistapplication.home

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolistapplication.TodoItem
import com.example.todolistapplication.TodoItemDataModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    navController: NavHostController,
    todoViewModel: HomeScreenViewModel,
    modifier: Modifier = Modifier
) {
    val todoItems by todoViewModel.allTodoItems.collectAsState(initial = emptyList())

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "TODO LIST APP")
                }
            )
        },
        bottomBar = {
            BottomAppBar(
                actions = {
                    Button(
                        modifier = Modifier.padding(10.dp),
                        onClick = {
                            navController.navigate("todo_detail/new")
                        }
                    ) {
                        Text("Add")
                    }
                }
            )
        }
    ) { contentPadding ->
        LazyColumn(modifier = Modifier.padding(contentPadding)) {
            items(todoItems) { item ->
                TodoItem(
                    navController = navController,
                    data = TodoItemDataModel(
                        id = item.id.toString(),
                        text = item.text,
                        isDone = item.isDone
                    ),
                    onUpdate = {navController.navigate("todo_detail/${item.id}")},
                    onRemove = {todoViewModel.delete(it)}
                )
            }
        }
    }
}

// TODO: Bring this back
//@Preview(showBackground = true)
//@Composable
//fun HomeScreenPreview() {
//    val navController = rememberNavController()
//    HomeScreen(navController)
//}