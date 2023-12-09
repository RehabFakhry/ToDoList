package com.areebgroup.todolist.data.source.local.model

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "todo_list")
data class TodoItem(
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val id: Int,
    @ColumnInfo(name = "status")
    val status: Boolean,
    @ColumnInfo(name = "taskTitle")
    var taskTitle: String,
    @ColumnInfo(name = "taskDescription")
    var taskDescription: String
)