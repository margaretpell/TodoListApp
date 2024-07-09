package com.example.todolistapplication

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController

// TODO: Rename the file to be more descriptive
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Details(
    navController: NavHostController,
    todoViewModel: TodoViewModel,
    data: TodoItemDataModel? = null,
    modifier: Modifier = Modifier
) {
    var text by remember { mutableStateOf(TextFieldValue(data?.text ?: "")) }

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
                value = text,
                onValueChange = { newText ->
                    text = newText
                }
            )
            Spacer(modifier.padding(10.dp))
            Button(modifier = Modifier.fillMaxWidth(),
                onClick = {
                    if (data != null) {
                        todoViewModel.update(
                            TodoItemEntity(
                                id = data.id.toInt(),
                                text = text.text,
                                isDone = data.isDone
                            )
                        )
                    } else {
                        todoViewModel.insert(
                            TodoItemEntity(
                                text = text.text
                            )
                        )
                    }
                    navController.navigate("home")

                }) {
                Text("Save")
            }
        }
    }
}


// TODO: Bring this back
//@Preview(showBackground = true)
//@Composable
//fun DetailsPreview() {
//    val navController = rememberNavController()
//    Details(navController, data = TodoItemDataModel(
//        id = "5",
//        text = "Hiiiiii",
//        isDone = false
//    ))
//}