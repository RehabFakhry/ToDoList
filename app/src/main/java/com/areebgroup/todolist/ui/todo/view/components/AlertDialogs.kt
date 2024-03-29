package com.areebgroup.todolist.ui.todo.view.components

import  androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areebgroup.todolist.R
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.ui.todo.model.ToDoList

@Composable
fun Dialogs(
    isUpdateDialogVisible: Boolean,
    selectedTaskForUpdate: TodoItem?,
    isDialogVisible: Boolean,
    todolist: ToDoList,
    onClickAddTask: (TodoItem) -> Unit,
    onClickUpdate: (TodoItem) -> Unit,
    onCancelDialog: () -> Unit
) {
    if (isUpdateDialogVisible && selectedTaskForUpdate != null) {
        TaskDialog(
            todoTask = selectedTaskForUpdate,
            dialogTitle = stringResource(id = R.string.update_task),
            confirmText = stringResource(id = R.string.update),
            onClickConfirm = { updatedTask ->
                onClickUpdate(updatedTask)
                onCancelDialog()
            },
            onClickCancel = onCancelDialog
        )
    } else if (isDialogVisible) {
        TaskDialog(
            dialogTitle = stringResource(id = R.string.add_new_task),
            confirmText = stringResource(id = R.string.add),
            todoTask = todolist.todoItem,
            onClickConfirm = { newTask ->
                onClickAddTask(newTask)
                onCancelDialog()
            },
            onClickCancel = onCancelDialog
        )
    }
}

@Composable
fun TaskDialog(
    todoTask: TodoItem,
    dialogTitle: String,
    confirmText: String,
    onClickConfirm: (TodoItem) -> Unit,
    onClickCancel: () -> Unit,
) {
    var taskTitle by remember { mutableStateOf("") }
    var taskDescription by remember { mutableStateOf("") }

    AlertDialog(
        onDismissRequest = { onClickCancel() },
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.app_icon),
                    contentDescription = null,
                    modifier = Modifier
                        .background(Color.Transparent)
                        .size(48.dp)
                )
                Text(text = dialogTitle, fontSize = 20.sp)
            }
        },
        text = {
            Column {
                CustomTextField(
                    modifier = Modifier.padding(bottom = 8.dp),
                    value = taskTitle,
                    onValueChange = { taskTitle = it },
                    label = stringResource(R.string.title)
                )
                CustomTextField(
                    value = taskDescription,
                    onValueChange = { taskDescription = it },
                    label = stringResource(R.string.description)
                )
            }
        },
        confirmButton = {
            Button(
                onClick = {
                    val newAddedTask = todoTask.copy(
                        taskTitle = taskTitle,
                        taskDescription = taskDescription
                    )
                    onClickConfirm(newAddedTask)
                    taskTitle = ""
                    taskDescription = ""
                }
            ) { Text(confirmText) }
        },
        dismissButton = {
            Button(
                onClick = {
                    onClickCancel()
                }
            ) { Text(stringResource(R.string.cancel)) }
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CustomTextField(
    value: String,
    onValueChange: (String) -> Unit,
    label: String,
    modifier: Modifier = Modifier,
) {
    TextField(
        modifier = modifier
            .fillMaxWidth()
            .wrapContentHeight()
            .background(Color.Transparent),
        value = value,
        singleLine = true,
        onValueChange = { onValueChange(it) },
        label = { Text(text = label, color = MaterialTheme.colorScheme.primary) },
        shape = RoundedCornerShape(8.dp),
        colors = TextFieldDefaults.textFieldColors(
            cursorColor = MaterialTheme.colorScheme.primary,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
        )
    )
}