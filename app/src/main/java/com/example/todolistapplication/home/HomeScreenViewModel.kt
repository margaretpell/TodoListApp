package com.example.todolistapplication.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.todolistapplication.TodoRepository
import com.example.todolistapplication.TodoRepositoryImpl
import com.example.todolistapplication.db.TodoDatabase
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class HomeScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository

    private val _allTodoItems = MutableStateFlow<List<TodoItemEntity>>(emptyList())
    val allTodoItems: StateFlow<List<TodoItemEntity>> = _allTodoItems

    init {
        val todoItemDao = TodoDatabase.getDatabase(application).todoItemDao()
        repository = TodoRepositoryImpl(todoItemDao)
        loadAllTodoItems()
    }

    private fun loadAllTodoItems(){
        viewModelScope.launch {
            repository.getAllTodoItems().collect{ items ->
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