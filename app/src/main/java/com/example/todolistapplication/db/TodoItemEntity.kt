package com.example.todolistapplication.db

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "todo_items")
data class TodoItemEntity(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val text: String,
    val isDone: Boolean = false
)