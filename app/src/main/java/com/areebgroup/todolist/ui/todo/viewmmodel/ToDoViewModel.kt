package com.areebgroup.todolist.ui.todo.viewmmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.domain.usecase.AddNewTaskUseCase
import com.areebgroup.todolist.domain.usecase.DeleteTaskUseCase
import com.areebgroup.todolist.domain.usecase.GetAllTodoListUseCase
import com.areebgroup.todolist.domain.usecase.MarkTaskCompletedUseCase
import com.areebgroup.todolist.domain.usecase.UpdateTaskUseCase
import com.areebgroup.todolist.ui.todo.view.ToDoIntent
import com.areebgroup.todolist.ui.todo.model.ToDoList
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class ToDoViewModel @Inject constructor(
    private val addNewTaskUseCase: AddNewTaskUseCase,
    private val updateTaskUseCase: UpdateTaskUseCase,
    private val deleteTaskUseCase: DeleteTaskUseCase,
    private val getAllTodoTasksUseCase: GetAllTodoListUseCase,
    private val markTaskCompletedUseCase: MarkTaskCompletedUseCase,
) : ViewModel() {

    private val _state = MutableStateFlow(ToDoList())
    val state: StateFlow<ToDoList> get() = _state

    private val _toastMessage = MutableStateFlow<String?>(null)
    val toastMessage: StateFlow<String?> get() = _toastMessage

    init {
        processing()
    }

    fun processing() {
        viewModelScope.launch {
            loadTodoList()
        }
    }

    fun processIntent(intent: ToDoIntent) {
        viewModelScope.launch {
            try {
                when (intent) {
                    is ToDoIntent.LoadTodoList -> loadTodoList()
                    is ToDoIntent.AddTask -> addTask(intent.newTask)
                    is ToDoIntent.DeleteTask -> deleteTask(intent.id)
                    is ToDoIntent.UpdateTask -> updateTask(intent.updatedTask)
                    is ToDoIntent.MarkTaskCompleted -> onTaskCompleted(intent.id, intent.status)
                }
            } catch (e: Exception) {
                handleError("Error Processing Intent", e)
            }
        }
    }

    private suspend fun loadTodoList() {
        getAllTodoTasksUseCase().collect { tasks ->
            _state.value = ToDoList(todoList = tasks)
        }
    }

    private suspend fun addTask(newTask: TodoItem) {
        addNewTaskUseCase(newTask)
        loadTodoList()
    }

    private suspend fun updateTask(updatedTask: TodoItem) {
        val updatedTodoList = updateTaskUseCase(_state.value.todoList, updatedTask)
        _state.value = _state.value.copy(todoList = updatedTodoList)
    }

    private suspend fun deleteTask(id: Int) {
        val updatedTodoList = deleteTaskUseCase(_state.value.todoList, id)
        _state.value = _state.value.copy(todoList = updatedTodoList)
        _toastMessage.value = "Task deleted successfully!"
    }

    private suspend fun onTaskCompleted(id: Int, status: Boolean) {
        val updatedTask = markTaskCompletedUseCase(_state.value.todoList, id, status)
        _state.value = _state.value.copy(todoList = updatedTask)
    }

    private suspend inline fun handleError(errorMessage: String, e: Exception) {
        withContext(Dispatchers.Main) {
            _toastMessage.value = "$errorMessage: ${e.message}"
        }
    }

    fun clearToastMessage() {
        _toastMessage.value = null
    }
}