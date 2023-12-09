package com.areebgroup.todolist.domain.usecase

import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.domain.repository.TodoRepository
import javax.inject.Inject

class AddNewTaskUseCase @Inject constructor(
    private val todoRepository: TodoRepository
) {
    suspend operator fun invoke(todoItem: TodoItem) {
        todoRepository.addTask(todoItem)
    }
}