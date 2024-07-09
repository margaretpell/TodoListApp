package com.example.todolistapplication

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapplication.db.TodoDatabase
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.launch

class TodoViewModel(application: Application) : AndroidViewModel(application){
    private val repository: TodoRepository

    // TODO: Change this to Flows. Hint: SharedFlow
    val allTodoItems: LiveData<List<TodoItemEntity>>

    init{
        // TODO: Extract to a separate function.
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