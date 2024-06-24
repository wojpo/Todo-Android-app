package com.example.todoapp

data class Todo(
    val task: String,
    val isCompleted: Boolean = false
)