package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.TODOAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOAPPTheme {
                MainScreen()
            }
        }
    }
}

@Composable
fun MainScreen() {
    var task by remember { mutableStateOf("") }
    val tasks = remember { mutableStateListOf<String>() }
    val checkboxStates = remember { mutableStateListOf<Boolean>() }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(color = Color.Black)
            .padding(WindowInsets.safeContent.asPaddingValues())
    ) {
        TaskInput(
            task = task,
            onTaskChange = { task = it },
            onAddTask = {
                if (task.isNotBlank()) {
                    tasks.add(task)
                    task = ""
                    checkboxStates.add(false)
                }
            }
        )
        TaskList(
            tasks = tasks,
            checkboxStates = checkboxStates,
            onTaskCheckedChange = { index, isChecked ->
                checkboxStates[index] = isChecked
            },
            onRemoveTask = { index ->
                tasks.removeAt(index)
                checkboxStates.removeAt(index)
            }
        )
    }
}

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
            colors = ButtonColors(Color.White,Color.Black,Color.White,Color.White),
            onClick = onAddTask,
            modifier = Modifier
                .padding(horizontal = 8.dp)
                .height(60.dp)
        ) {
            Text(text = "ADD TASK")
        }
    }
}

@Composable
fun TaskList(
    tasks: List<String>,
    checkboxStates: List<Boolean>,
    onTaskCheckedChange: (Int, Boolean) -> Unit,
    onRemoveTask: (Int) -> Unit
) {
    LazyColumn(
    ) {
        itemsIndexed(tasks) { index, item ->
            TaskItem(
                task = item,
                isChecked = checkboxStates[index],
                onCheckedChange = { onTaskCheckedChange(index, it) },
                onRemove = { onRemoveTask(index) }
            )
            HorizontalDivider(color = Color.White)
        }
    }
}

@Composable
fun TaskItem(
    task: String,
    isChecked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    onRemove: () -> Unit
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Text(
            text = task,
            color = Color.White,
            textAlign = TextAlign.Justify,
            modifier = Modifier
                .weight(1f)
                .padding(top = 12.dp)
        )
        Checkbox(
            checked = isChecked,
            onCheckedChange = onCheckedChange
        )
        IconButton(
            onClick = onRemove
        ) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Delete", tint = Color.White)
        }
    }
}