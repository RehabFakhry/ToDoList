package com.areebgroup.todolist.ui.todo.intent

import com.areebgroup.todolist.data.source.local.model.TodoItem

//Actions
sealed class ToDoIntent {
    data object LoadTodoList : ToDoIntent()
    data class AddTask(val newTask: TodoItem) : ToDoIntent()
    data class UpdateTask(val updatedTask: TodoItem) : ToDoIntent()
    data class MarkTaskCompleted(val id: Int, val status: Boolean) : ToDoIntent()
    data class DeleteTask(val id: Int) : ToDoIntent()
}
