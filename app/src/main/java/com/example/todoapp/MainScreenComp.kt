package com.example.todoapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun MainScreen(viewModel: TodoViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(WindowInsets.safeContent.asPaddingValues())
    ) {
        TaskInput(
            task = viewModel.task,
            onTaskChange = { viewModel.task = it },
            onAddTask = { viewModel.addTask() }
        )
        TaskList(
            tasks = viewModel.tasks,
            onTaskCheckedChange = { index, isChecked ->
                viewModel.updateTask(index, isChecked)
            },
            onRemoveTask = { index ->
                viewModel.removeTask(index)
            }
        )
    }
}
