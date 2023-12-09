package com.areebgroup.todolist.ui.todo.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.areebgroup.todolist.R

@Composable
fun EmptyPlaceholder() {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Image(
            modifier = Modifier.size(300.dp),
            painter = painterResource(id = R.drawable.empty_placeholder),
            contentDescription = "Placeholder Image",
            contentScale = ContentScale.Crop
        )
    }
}