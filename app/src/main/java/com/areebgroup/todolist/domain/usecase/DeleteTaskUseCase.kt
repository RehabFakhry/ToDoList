package com.areebgroup.todolist.domain.usecase

import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.domain.repository.TodoRepository
import javax.inject.Inject

class DeleteTaskUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(tasks: List<TodoItem>, id: Int): List<TodoItem> {
        todoRepository.deleteTask(id)
        return tasks.filter { it.id != id }
    }
}