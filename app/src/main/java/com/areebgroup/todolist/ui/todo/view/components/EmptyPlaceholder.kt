package com.areebgroup.todolist.ui.todo.view.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.areebgroup.todolist.R
import com.areebgroup.todolist.ui.theme.white87

@Composable
fun EmptyPlaceholder() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp).padding(top = 56.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(modifier = Modifier
            .size(150.dp, 250.dp),
            contentAlignment = Alignment.Center) {
            Image(
                modifier = Modifier.fillMaxSize(),
                painter = painterResource(id = R.drawable.empty_placeholder),
                contentDescription = stringResource(R.string.placeholder_image),
                contentScale = ContentScale.Crop
            )
        }
        Text(
            modifier = Modifier.padding(top = 24.dp),
            text = stringResource(R.string.placeholder_text),
            textAlign = TextAlign.Center,
            fontSize = 14.sp,
            color = white87,
        )
    }
}