package com.example.todoapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContent
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.todoapp.ui.theme.TODOAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOAPPTheme()
            {
                var task by remember {
                    mutableStateOf("")
                }
                var tasks by remember {
                    mutableStateOf(listOf<String>(""))
                }
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(150.dp)
                    )
                    {
                        OutlinedTextField(
                            value = task,
                            onValueChange = { text ->
                                task = text
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(WindowInsets.safeContent.asPaddingValues())
                                .padding(horizontal = 8.dp)

                        )
                        Button(
                            colors = ButtonColors(Color.White,Color.Black,Color.White,Color.White),
                            onClick = { tasks = tasks + task },
                            modifier = Modifier
                                .padding(WindowInsets.safeContent.asPaddingValues())
                                .padding(horizontal = 8.dp)
                                .fillMaxHeight()
                        )
                        {
                            Text(text = "ADD TASK");
                        }
                    }
                }
            }
        }

    }
}

