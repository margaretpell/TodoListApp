package com.example.todolistapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.todolistapplication.db.TodoItemDao
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// TODO: Create an interface for this and implement it
class TodoRepository(private val todoItemDao: TodoItemDao) {

    // TODO: Don't fetch until asked, create a separate method for this.
    val allTodoItems: Flow<List<TodoItemEntity>> = todoItemDao.getAll().asFlow().map { it };

//    suspend fun getAllTodoItems(): LiveData<List<TodoItemEntity>> {
//        return todoItemDao.getAll()
//    }

    suspend fun getItemById(itemId: String): Flow<TodoItemEntity> {
        return todoItemDao.getItemById(itemId.toInt()).asFlow().map { it }
    }


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
