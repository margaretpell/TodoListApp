package com.example.todolistapplication.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update


@Dao
interface TodoItemDao {

    @Query("SELECT * FROM todo_items WHERE id=:itemId")
    fun getItemById(itemId: Int): LiveData<TodoItemEntity>

    @Query("SELECT * FROM todo_items")
    fun getAll(): LiveData<List<TodoItemEntity>>

    // TODO: Write query to fetch a single todo item with id.

    @Insert
    suspend fun insert(todoItem: TodoItemEntity)

    @Update
    suspend fun update(todoItem: TodoItemEntity)
    
    @Delete
    suspend fun delete(todoItem: TodoItemEntity)
}