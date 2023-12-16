package com.areebgroup.todolist.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.areebgroup.todolist.ui.theme.ToDoListTheme
import com.areebgroup.todolist.ui.theme.darkBackground
import com.areebgroup.todolist.ui.todo.view.HomeScreen
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ToDoListTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = darkBackground
                ) {
                    HomeScreen()
                }
            }
        }
    }
}