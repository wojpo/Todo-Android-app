package com.example.todoapp

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class TodoViewModel(context: Context) : ViewModel() {
    var task by mutableStateOf("")
    var tasks = mutableStateListOf<Todo>()
        private set

    init {
        tasks.addAll(loadTasks(context))
    }

    fun addTask() {
        if (task.isNotBlank()) {
            tasks.add(
                Todo(
                task = task,
                isCompleted = false
            )
            )
            task = ""
        }
    }

    fun removeTask(index: Int) {
        tasks.removeAt(index)
    }

    fun updateTask(index: Int, isCompleted: Boolean) {
        val todo = tasks[index]
        tasks[index] = todo.copy(isCompleted = isCompleted)
    }

    fun saveTasks(context: Context) {
        saveTasks(context, tasks)
    }
}