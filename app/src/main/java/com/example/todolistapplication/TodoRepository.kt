package com.example.todolistapplication

import androidx.lifecycle.LiveData

class TodoRepository(private val todoItemDao: TodoItemDao) {
    val allTodoItems: LiveData<List<TodoItemEntity>> = todoItemDao.getAll();

    suspend fun insert(todoItem: TodoItemEntity){
        todoItemDao.insert(todoItem)
    }

    suspend fun update(todoItem: TodoItemEntity){
        todoItemDao.update(todoItem)
    }

    suspend fun delete(todoItem: TodoItemEntity){
        todoItemDao.delete(todoItem)
    }
}