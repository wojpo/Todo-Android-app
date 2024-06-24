package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.todoapp.ui.theme.TODOAPPTheme

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
