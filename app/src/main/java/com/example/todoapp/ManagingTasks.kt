package com.example.todoapp

import android.content.Context
import java.io.File

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