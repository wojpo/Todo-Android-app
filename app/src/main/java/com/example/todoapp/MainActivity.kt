package com.example.todoapp

import android.content.Context
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
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.TODOAPPTheme
import java.io.File

class MainActivity : ComponentActivity() {
    private lateinit var todoViewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        todoViewModel = TodoViewModel(applicationContext)

        setContent {
            TODOAPPTheme {
                MainScreen(todoViewModel)
            }
        }
    }

    override fun onStop() {
        super.onStop()
        todoViewModel.saveTasks(applicationContext)
    }
}

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


fun saveTasks(context: Context, tasks: List<Todo>) {
    val file = File(context.filesDir, "tasks.txt")
    file.printWriter().use { out ->
        tasks.forEach {
            out.println("${it.task}|${it.isCompleted}")
        }
    }
}

fun loadTasks(context: Context): List<Todo> {
    val file = File(context.filesDir, "tasks.txt")
    if (!file.exists()) return emptyList()

    return file.readLines().map {
        val parts = it.split("|")
        Todo(
            task = parts[0],
            isCompleted = parts[1].toBoolean()
        )
    }
}
