package com.areebgroup.todolist.ui.todo.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.ui.theme.Pink40
import com.areebgroup.todolist.ui.todo.intent.ToDoIntent
import com.areebgroup.todolist.ui.todo.model.ToDoList
import com.areebgroup.todolist.ui.todo.view.components.ContentSection
import com.areebgroup.todolist.ui.todo.view.components.Dialogs
import com.areebgroup.todolist.ui.todo.viewmmodel.ToDoViewModel

@Composable
fun HomeScreen(
    viewModel: ToDoViewModel = hiltViewModel(),
) {
    val todoState by viewModel.state.collectAsState()
    HomeContent(
        todoViewModel = viewModel,
        todolist = todoState,
        onMarkCheckBox = { taskId, taskStatus ->
            viewModel.processIntent(ToDoIntent.MarkTaskCompleted(taskId, taskStatus))
        },
        onClickAddTask = {
            viewModel.processIntent(ToDoIntent.AddTask(it))
        },
        onClickUpdate = { todoItem, onClick ->
            viewModel.processIntent(ToDoIntent.UpdateTask(todoItem))
            onClick()
        },
        onClickDelete = {
            viewModel.processIntent(ToDoIntent.DeleteTask(it))
        },
    )
}


@SuppressLint("RememberReturnType")
@Composable
fun HomeContent(
    todolist: ToDoList,
    todoViewModel: ToDoViewModel,
    onMarkCheckBox: (Int, Boolean) -> Unit,
    onClickDelete: (Int) -> Unit,
    onClickUpdate: (updatedTask: TodoItem, onClick: () -> Unit) -> Unit,
    onClickAddTask: (newTask: TodoItem) -> Unit
) {
    var isDialogVisible by remember { mutableStateOf(false) }
    var isUpdateDialogVisible by remember { mutableStateOf(false) }

    val context = LocalContext.current

    val toastMessage by todoViewModel.toastMessage.collectAsState()
    if (!toastMessage.isNullOrBlank()) {
        Toast.makeText(context, toastMessage, Toast.LENGTH_LONG).show()
        todoViewModel.clearToastMessage()
    }

    Box(modifier = Modifier, contentAlignment = Alignment.BottomEnd) {
        FloatingActionButton(
            onClick = { isDialogVisible = true },
            containerColor = Pink40,
            modifier = Modifier
                .size(70.dp)
                .padding(bottom = 16.dp, end = 16.dp)
        ) {
            Icon(imageVector = Icons.Default.Add, contentDescription = null)
        }

        ContentSection(todolist, onMarkCheckBox, onClickDelete) { task ->
            onClickUpdate(task)
            {
                isUpdateDialogVisible = true
            }
        }

        Dialogs(
            isDialogVisible = isDialogVisible,
            isUpdateDialogVisible = isUpdateDialogVisible,
            todolist = todolist,
            onClickAddTask = { newTask ->
                isDialogVisible = false
                onClickAddTask(newTask)
                Toast.makeText(context, "Task added successfully", Toast.LENGTH_SHORT).show()
            },
            onClickUpdateTask = { updatedTask ->
                onClickUpdate(
                    updatedTask
                ) {
                    isUpdateDialogVisible = false
                }
            },
            onCancelDialog = {
                isDialogVisible = false
                isUpdateDialogVisible = false
            }
        )
    }
}