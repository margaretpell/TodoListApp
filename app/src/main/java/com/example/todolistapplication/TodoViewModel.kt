package com.example.todolistapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application){
    private val repository: TodoRepository
    val allTodoItems: LiveData<List<TodoItemEntity>>

    init{
        val todoItemDao = TodoDatabase.getDatabase(application).todoItemDao()
        repository = TodoRepository(todoItemDao)
        allTodoItems = repository.allTodoItems
    }

    fun insert(todoItem : TodoItemEntity) = viewModelScope.launch{
        repository.insert(todoItem)
    }

    fun update(todoItem: TodoItemEntity) = viewModelScope.launch{
        repository.update(todoItem)
    }

    fun delete(todoItem: TodoItemEntity) = viewModelScope.launch{
        repository.delete(todoItem)
    }
}