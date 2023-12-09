package com.areebgroup.todolist.domain.usecase

import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.domain.repository.TodoRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class GetAllTodoListUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    fun invoke(): Flow<List<TodoItem>> =
        todoRepository.getAllTodoTasks()
}