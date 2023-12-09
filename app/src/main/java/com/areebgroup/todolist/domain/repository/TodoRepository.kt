package com.areebgroup.todolist.domain.repository

import com.areebgroup.todolist.data.source.local.model.TodoItem
import kotlinx.coroutines.flow.Flow

interface TodoRepository {

    fun getAllTodoTasks(): Flow<List<TodoItem>>

    suspend fun addTask(newTask: TodoItem)

    suspend fun updateTask(updatedTask: TodoItem)

    suspend fun deleteTask(id: Int)

    suspend fun markTaskCompleted(id: Int, status: Boolean)

}