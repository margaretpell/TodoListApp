package com.example.todolistapplication

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

@Dao
interface TodoItemDao {
    @Query("SELECT * FROM todo_items")
    fun getAll(): LiveData<List<TodoItemEntity>>

    @Insert
    suspend fun insert(todoItem: TodoItemEntity)

    @Update
    suspend fun update(todoItem: TodoItemEntity)
    
    @Delete
    suspend fun delete(todoItem: TodoItemEntity)
}