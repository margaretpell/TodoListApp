package com.example.todolistapplication.details


import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import androidx.lifecycle.viewModelScope
import com.example.todolistapplication.TodoRepository
import com.example.todolistapplication.db.TodoDatabase
import com.example.todolistapplication.db.TodoItemEntity
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailsScreenViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: TodoRepository

    // TODO: Change this to Flows. Hint: SharedFlow(addressed)
    private val _todoItemToDisplay = MutableStateFlow<TodoItemEntity?>(null);
    val todoItemToDisplay: StateFlow<TodoItemEntity?> = _todoItemToDisplay

    init {
        // TODO: Extract to a separate function.
        val todoItemDao = TodoDatabase.getDatabase(application).todoItemDao()
        repository = TodoRepository(todoItemDao)
    }

    suspend fun loadTodoToDisplay(itemId: String) {
        viewModelScope.launch {
            repository.getItemById(itemId).collect { item ->
                _todoItemToDisplay.value = item
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