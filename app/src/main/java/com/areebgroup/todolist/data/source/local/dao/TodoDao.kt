package com.areebgroup.todolist.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.areebgroup.todolist.data.source.local.model.TodoItem
import kotlinx.coroutines.flow.Flow

@Dao
interface TodoDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(todoItem: TodoItem)

    @Query("SELECT * FROM todo_list")
    fun getAllTodoTasks(): Flow<List<TodoItem>>

    @Update
    suspend fun updateTask(todoItem: TodoItem)

    @Query("UPDATE todo_list SET status = :status WHERE id = :id")
    suspend fun markTaskCompleted(id: Int, status: Boolean)

    @Query("DELETE FROM todo_list WHERE id = :id")
    suspend fun deleteTask(id: Int)
}