package com.example.todoapp

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

@Composable
fun TaskList(
    tasks: List<Todo>,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
    onRemoveTask: (Int) -> Unit
) {
    LazyColumn {
        itemsIndexed(tasks) { index, item ->
            TaskItem(
                task = item,
                onCheckedChange = { onTaskCheckedChange(index, it) },
                onRemove = { onRemoveTask(index) }
            )
            HorizontalDivider(color = Color.White)
        }
    }
}