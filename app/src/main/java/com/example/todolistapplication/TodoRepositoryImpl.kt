package com.example.todolistapplication

import androidx.lifecycle.LiveData
import androidx.lifecycle.asFlow
import com.example.todolistapplication.db.TodoItemDao
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


// TODO: Create an interface for this and implement it
class TodoRepositoryImpl(private val todoItemDao: TodoItemDao) : TodoRepository {


    override fun getAllTodoItems(): Flow<List<TodoItemEntity>> {
        return todoItemDao.getAll().asFlow().map{it}
    }

    override fun getItemById(itemId: String): Flow<TodoItemEntity> {
        return todoItemDao.getItemById(itemId.toInt()).asFlow().map { it }
    }


    override suspend fun insert(todoItem: TodoItemEntity){
        todoItemDao.insert(todoItem)
    }

    override suspend fun update(todoItem: TodoItemEntity){
        todoItemDao.update(todoItem)
    }

    override suspend fun delete(todoItem: TodoItemEntity){
        todoItemDao.delete(todoItem)
    }
}