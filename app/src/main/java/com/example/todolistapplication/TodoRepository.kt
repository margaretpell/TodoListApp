package com.example.todolistapplication

import androidx.lifecycle.LiveData
import com.example.todolistapplication.db.TodoItemDao
import com.example.todolistapplication.db.TodoItemEntity


// TODO: Create an interface for this and implement it
class TodoRepository(private val todoItemDao: TodoItemDao) {

    // TODO: Don't fetch until asked, create a separate method for this.
    val allTodoItems: LiveData<List<TodoItemEntity>> = todoItemDao.getAll();

    // TODO: Write query to fetch a single todo item with id.

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
