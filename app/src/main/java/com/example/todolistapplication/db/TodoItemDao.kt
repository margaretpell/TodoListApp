package com.example.todolistapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update

// TODO: Move to database package
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