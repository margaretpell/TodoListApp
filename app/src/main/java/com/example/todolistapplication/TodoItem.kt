package com.example.todolistapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.asFlow


@Composable
fun TodoItem(
    navController: NavHostController,
    data: TodoItemDataModel,
    onUpdate:(TodoItemEntity) -> Unit,
    onRemove:(TodoItemEntity) -> Unit,
    modifier: Modifier = Modifier
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(
                vertical = 8.dp,
                horizontal = 16.dp
            ),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(
            text = data.text,
            modifier = Modifier
        )

        // Add Update and Remove button for updating the content and remove the content.
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            Button(onClick = { navController.navigate("todo_detail/${data.id}") }) {
                Text("Update")
            }
            Button(onClick = {
                onRemove(
                    TodoItemEntity(
                        id = data.id.toInt(),
                        text = data.text,
                        isDone = data.isDone
                    )
                )
            }) {
                Text("Remove")
            }
        }

    }
}


@Preview(showBackground = true)
@Composable
fun TodoItemPreview() {
    val navController = rememberNavController()
    TodoItem(
        navController = navController,
        data = TodoItemDataModel(
            id = "0",
            text = "Buy Groceries",
            isDone = false
        ),
        onUpdate = {},
        onRemove = {}
    )
}

@Preview(showBackground = true)
@Composable
fun TodoItemsListPreview() {
    val navController = rememberNavController()
    val todoItems = listOf(
        TodoItemDataModel(id = "1", text = "Sample1"),
        TodoItemDataModel(id = "2", text = "Sample2"),
        TodoItemDataModel(id = "3", text = "Sample3")
    )
    LazyColumn {
        items(todoItems){
            item ->
            TodoItem(
                navController = navController,
                data = item,
                onUpdate = {} ,
                onRemove = {})
        }
    }
}