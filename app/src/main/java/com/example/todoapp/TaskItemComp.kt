package com.example.todoapp

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun TaskItem(
    task: Todo,
    onCheckedChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = task.task,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp)
        )
        Checkbox(
            checked = task.isCompleted,
            onCheckedChange = onCheckedChange
        )
        IconButton(
            onClick = onRemove
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
        }
    }
}