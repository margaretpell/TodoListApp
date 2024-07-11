package com.example.todolistapplication.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase



@Database(entities = [TodoItemEntity::class], version = 1)
abstract class TodoDatabase : RoomDatabase() {
    abstract fun todoItemDao(): TodoItemDao

    companion object {
        @Volatile
        private var instance: TodoDatabase? = null

        fun getDatabase(context: Context): TodoDatabase =
            instance ?: synchronized(this) {
                instance ?: builtDatabase(context).also {
                    instance = it
                }
            }

        private fun builtDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            TodoDatabase::class.java,
            "todo_database"
        ).build()
    }
}