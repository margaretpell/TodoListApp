package com.example.todolistapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapplication.TodoRepository
import com.example.todolistapplication.db.TodoDatabase
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository

    // TODO: Change this to Flows. Hint: SharedFlow(addressed)
    private val _allTodoItems = MutableStateFlow<List<TodoItemEntity>>(emptyList())
    val allTodoItems: StateFlow<List<TodoItemEntity>> = _allTodoItems

    init {
        // TODO: Extract to a separate function.(addressed)
        val todoItemDao = TodoDatabase.getDatabase(application).todoItemDao()
        repository = TodoRepository(todoItemDao)
        loadAllTodoItems()
    }

    private fun loadAllTodoItems(){
        viewModelScope.launch {
            repository.allTodoItems.collect{ items ->
                _allTodoItems.value = items
            }
        }
    }

    fun insert(todoItem: TodoItemEntity) = viewModelScope.launch {
        repository.insert(todoItem)
    }

    fun update(todoItem: TodoItemEntity) = viewModelScope.launch {
        repository.update(todoItem)
    }

    fun delete(todoItem: TodoItemEntity) = viewModelScope.launch {
        repository.delete(todoItem)
    }
}