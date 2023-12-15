package com.areebgroup.todolist.data.source.local.dao

import androidx.room.Database
import androidx.room.RoomDatabase
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.data.source.local.dao.TodoDao

@Database(entities = [TodoItem::class], version = 1, exportSchema = false)
abstract class TodoDatabase: RoomDatabase() {
    abstract val todoDao: TodoDao
}