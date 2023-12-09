package com.areebgroup.todolist.ui.todo.view

import android.annotation.SuppressLint
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import com.areebgroup.todolist.R
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.ui.theme.Pink40
import com.areebgroup.todolist.ui.theme.white37
import com.areebgroup.todolist.ui.theme.white87
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
        onClickUpdate = {
            viewModel.processIntent(ToDoIntent.UpdateTask(it))
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
    onClickUpdate: (updatedTask: TodoItem) -> Unit,
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

        ContentSection(todolist, onMarkCheckBox, onClickDelete, onClickUpdate)

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
                isUpdateDialogVisible = false
                onClickUpdate(updatedTask)
            },
            onCancelDialog = { isDialogVisible = false }
        )
    }
}