package com.areebgroup.todolist.ui.todo.view.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreVert
import androidx.compose.material3.Checkbox
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areebgroup.todolist.R
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.ui.theme.white37
import com.areebgroup.todolist.ui.theme.white87

@Composable
fun TasksList(
    todoList: List<TodoItem>,
    onMarkCheckBox: (Int, Boolean) -> Unit,
    onClickUpdate: (TodoItem) -> Unit,
    onClickDelete: (Int) -> Unit
) {
    LazyColumn(
        modifier = Modifier
    ) {
        items(todoList.size) { index ->
            val todo = todoList[index]
            ToDoList(
                todoItem = todo,
                onCheckBoxClicked = { id, isChecked ->
                    onMarkCheckBox(id, isChecked)
                },
                onClickUpdate = { updatedTask ->
                    onClickUpdate(updatedTask)
                },
                onClickDelete = { id ->
                    onClickDelete(id)
                }
            )
        }
    }
}

@Composable
fun ToDoList(
    todoItem: TodoItem,
    modifier: Modifier = Modifier,
    onCheckBoxClicked: (Int, Boolean) -> Unit,
    onClickUpdate: (TodoItem) -> Unit,
    onClickDelete: (Int) -> Unit
) {
    var expandedMenu by remember { mutableStateOf(false) }

    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp, horizontal = 8.dp)
            .clickable {
                onCheckBoxClicked(todoItem.id, !todoItem.status)
            }
    ) {
        Checkbox(
            modifier = Modifier.padding(top = 4.dp),
            checked = todoItem.status,
            onCheckedChange = { isChecked ->
                onCheckBoxClicked(todoItem.id, isChecked)
            })

        Column(
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(
                text = todoItem.taskTitle, fontSize = 18.sp,
                color = if (todoItem.status) Color.LightGray else white87,
                textDecoration = if (todoItem.status)
                    TextDecoration.LineThrough else TextDecoration.None
            )
            Text(
                text = todoItem.taskDescription, fontSize = 14.sp,
                color = if (todoItem.status) Color.LightGray else white37,
                textDecoration = if (todoItem.status)
                    TextDecoration.LineThrough else TextDecoration.None
            )
        }

        Box(
            modifier = Modifier
                .padding(top = 14.dp, end = 4.dp)
                .clickable { expandedMenu = !expandedMenu },
            contentAlignment = Alignment.CenterEnd,
        ) {
            Icon(
                imageVector = Icons.Default.MoreVert,
                contentDescription = "Options",
                tint = Color.White
            )

            DropdownMenu(
                expanded = expandedMenu,
                onDismissRequest = { expandedMenu = false }
            ) {
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.update)) },
                    onClick = {
                        expandedMenu = false
                        onClickUpdate(todoItem)
                    })
                DropdownMenuItem(
                    text = { Text(stringResource(R.string.delete)) },
                    onClick = {
                        expandedMenu = false
                        onClickDelete(todoItem.id)
                    })
            }
        }
    }
}