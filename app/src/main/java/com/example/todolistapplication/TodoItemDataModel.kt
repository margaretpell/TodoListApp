package com.example.todolistapplication

data class TodoItemDataModel (
    val id: String,
    val text: String,
    val isDone: Boolean = false,
)
