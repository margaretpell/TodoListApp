package com.example.todolistapplication


import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.Flow



interface TodoRepository {
    fun getAllTodoItems(): Flow<List<TodoItemEntity>>
    fun getItemById(itemId: String): Flow<TodoItemEntity>
    suspend fun insert(todoItemEntity: TodoItemEntity)
    suspend fun update(todoItemEntity: TodoItemEntity)
    suspend fun delete(todoItemEntity: TodoItemEntity)
}