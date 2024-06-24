package com.example.todoapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun TaskInput(
    task: String,
    onTaskChange: (String) -> Unit,
    onAddTask: () -> Unit
) {
    Row(
        modifier = Modifier.fillMaxWidth()
    ) {
        TextField(
            value = task,
            onValueChange = onTaskChange,
            modifier = Modifier
                .weight(1f)
                .padding(horizontal = 8.dp)
                .height(60.dp)
        )
        Button(
            onClick = onAddTask,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(60.dp)
        ) {
            Text(text = "ADD TASK")
        }
    }
}