package com.example.todolistapplication

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.todolistapplication.db.TodoItemEntity


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

// TODO: Bring the previews back
//@Preview(showBackground = true)
//@Composable
//fun TodoItemPreview() {
//    val navController = rememberNavController()
//    TodoItem(
//        navController = navController,
//        data = TodoItemDataModel(
//            id = "0",
//            text = "Buy Groceries",
//            isDone = false
//        )
//    )
//}
//
//@Preview(showBackground = true)
//@Composable
//fun TodoItemsListPreview() {
//    val myStringList = mutableListOf("#TODO1", "#TODO2", "#TODO3", "#TODO4")
//    val navController = rememberNavController()
//    LazyColumn {
//        for (i in myStringList) {
//            item {
//                TodoItem(
//                    navController = navController,
//                    data = TodoItemDataModel(
//                        id = i,
//                        text = "$i"
//                    )
//                )
//            }
//        }
//    }
//}