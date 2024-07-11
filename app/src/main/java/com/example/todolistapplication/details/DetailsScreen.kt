package com.example.todolistapplication.details

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapplication.db.TodoItemEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailsScreen(
    navController: NavHostController,
    viewModel: DetailsScreenViewModel,
    modifier: Modifier = Modifier,
    itemId: String? = null,
) {
    val todoItemToDisplay = viewModel.todoItemToDisplay.observeAsState().value
    var localText by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        if(itemId != null) {
            viewModel.loadTodoToDisplay(itemId)
        }
    }

    LaunchedEffect(todoItemToDisplay) {
        // If item id is not null, that means it is update case, not add.
        if(itemId != null) {
            localText = todoItemToDisplay?.text ?: ""
        }
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Description")
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate("home")
                        }
                    ) {
                        Icon(
                            imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                            contentDescription = "Navigate Back"
                        )
                    }
                },
            )
        }
    ) { contentPadding ->
        Column(
            modifier = Modifier
                .padding(contentPadding)
                .fillMaxSize()
                .padding(12.dp)
        ) {
            TextField(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                value = localText,
                onValueChange = { newText ->
                    localText = newText
                }
            )

            Spacer(modifier.padding(10.dp))

            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (todoItemToDisplay != null) {
                        viewModel.update(
                            TodoItemEntity(
                                id = todoItemToDisplay.id,
                                text = localText,
                                isDone = todoItemToDisplay.isDone
                            )
                        )
                    } else {
                        viewModel.insert(
                            TodoItemEntity(
                                text = localText
                            )
                        )
                    }

                    navController.popBackStack()
                }
            ) {
                Text("Save")
            }
        }
    }
}


// TODO: Bring this back
