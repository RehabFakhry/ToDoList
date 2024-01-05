package com.areebgroup.todolist.ui.todo.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areebgroup.todolist.R
import com.areebgroup.todolist.data.source.local.model.TodoItem
import com.areebgroup.todolist.ui.todo.model.ToDoList

@Composable
fun ContentSection(
    todolist: ToDoList,
    onMarkCheckBox: (Int, Boolean) -> Unit,
    onClickDelete: (Int) -> Unit,
    onClickUpdate: (todoItem: TodoItem) -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Row(
            modifier = Modifier.padding(top = 8.dp),
            horizontalArrangement = Arrangement.spacedBy(4.dp)
        ) {
            Image(
                modifier = Modifier
                    .padding(start = 8.dp)
                    .size(70.dp),
                painter = painterResource(id = R.drawable.app_icon),
                contentDescription = stringResource(R.string.app_icon)
            )

            Text(
                text = stringResource(R.string.todolist),
                fontSize = 32.sp,
                fontStyle = FontStyle.Italic,
                color = Color.White,
                modifier = Modifier.padding(top = 8.dp, start = 8.dp, bottom = 8.dp)
            )
        }

        Divider(modifier = Modifier.padding(top = 4.dp, bottom = 2.dp))

        if (todolist.todoList.isEmpty()) {
            EmptyPlaceholder()
        } else {
            TasksList(
                todolist.todoList,
                onMarkCheckBox,
                {
                todoItem ->
                onClickUpdate(todoItem)
            }
                ,onClickDelete
            )
        }
    }
}