package com.areebgroup.todolist.data.repository

import com.areebgroup.todolist.data.source.local.dao.TodoDao
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class TodoRepositoryImp @Inject constructor(
    private val todoDao: TodoDao
): TodoRepository {

    override fun getAllTodoTasks(): Flow<List<TodoItem>> =
        todoDao.getAllTodoTasks()

    override suspend fun addTask(newTask: TodoItem) =
        todoDao.insertTask(newTask.copy(id = 0, status = false))

    @OptIn(DelicateCoroutinesApi::class)
    override suspend fun updateTask(updatedTask: TodoItem) {
        GlobalScope.launch(Dispatchers.IO){
            todoDao.updateTask(updatedTask)
        }
    }

    override suspend fun deleteTask(id: Int) =
        todoDao.deleteTask(id)

    override suspend fun markTaskCompleted(id: Int, status: Boolean) {
        todoDao.markTaskCompleted(id, status)
    }
}