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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.Checkbox
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.example.todoapp.ui.theme.TODOAPPTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TODOAPPTheme()
            {
                var task by remember { mutableStateOf("") }
                var tasks by remember { mutableStateOf(listOf<String>()) }
                val checkboxStates = remember { mutableStateListOf<Boolean>() }
                Column(
                    modifier = Modifier
                        .fillMaxSize()
                        .background(color = Color.Black)
                        .padding(WindowInsets.safeContent.asPaddingValues())

                )
                {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()

                    )
                    {
                        TextField(
                            value = task,
                            onValueChange = { text ->
                                task = text
                            },
                            modifier = Modifier
                                .weight(1f)
                                .padding(horizontal = 8.dp)
                                .height(60.dp)

                        )
                        Button(
                            colors = ButtonColors(Color.White,Color.Black,Color.White,Color.White),
                            onClick = {if (task.isNotBlank()) { tasks = tasks + task; task = ""; checkboxStates.add(false)} },
                            modifier = Modifier
                                .padding(horizontal = 8.dp)
                                .height(60.dp)

                        )
                        {
                            Text(text = "ADD TASK");
                        }
                    }
                    LazyColumn(
                        modifier = Modifier
                            .weight(1f)
                    )
                    {
                        itemsIndexed(tasks) { index, item ->
                            Row(
                                modifier = Modifier
                                    .background(color = Color.Gray)
                                    .fillMaxWidth()
                                    .padding(16.dp)
                            )
                            {
                                Text(text = item, color = Color.White)
                                Checkbox(
                                    checked = checkboxStates[index],
                                    onCheckedChange = { isChecked ->
                                        checkboxStates[index] = isChecked
                                    }
                                )
                            }
                            HorizontalDivider(color = Color.White)

                        }
                    }
                }
            }
        }

    }
}

