package com.areebgroup.todolist.ui.todo.model

import com.areebgroup.todolist.data.source.local.model.TodoItem


data class ToDoList(
    val todoList: List<TodoItem> = emptyList(),
    val todoItem: TodoItem = TodoItem(0, false, "", "")
)