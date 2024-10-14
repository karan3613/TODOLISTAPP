package com.example.todolistapp.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.todolistapp.MyappViewModel
import com.example.todolistapp.Database.Task
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.navigation.NavHostController
import com.example.todolistapp.ui.theme.GreyBackGround
import com.example.todolistapp.ui.theme.NeonGreen

@Composable
fun MainScreen(navController: NavHostController, viewModel: MyappViewModel = hiltViewModel()){
    MainScreenUI(navController = navController , viewModel = viewModel)
}



@Composable
fun MainScreenUI(navController: NavHostController , viewModel: MyappViewModel) {
    val tasks by viewModel.tasks.collectAsState()
    LaunchedEffect(key1 = true) {
        viewModel.getAllTasks()
    }

    Column(
        modifier = Modifier
            .fillMaxSize().background(GreyBackGround).padding(5.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth().height(60.dp),
            verticalAlignment = Alignment.CenterVertically ,
            horizontalArrangement = Arrangement.End
        ) {
            Button(
                modifier = Modifier.padding(5.dp),
                onClick = {
                    navController.navigate("InsertScreen")
                } ,
                colors = ButtonDefaults.buttonColors(
                    containerColor = NeonGreen
                )
            ) {
                Text(
                    text = " Add Task" ,
                    fontWeight = FontWeight.SemiBold ,
                    color = Color.Black
                )
            }
        }
        Canvas(modifier = Modifier.fillMaxWidth()) {
            drawLine(
                color = NeonGreen,
                start = Offset(0f, size.height / 2),
                end = Offset(size.width, size.height / 2),
                strokeWidth = 2.dp.toPx()
            )
        }

        Box(modifier =  Modifier.fillMaxSize()) {
            if (tasks.isEmpty()) {
                Text(
                    text = "No tasks available. Click the + button to add one.",
                    modifier = Modifier.align(Alignment.Center),
                    color = NeonGreen ,
                    fontWeight = FontWeight.SemiBold
                )
            } else {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                ) {
                    items(tasks) { task ->
                        TaskCard(
                            task = task,
                            onCheckChange = { viewModel.update(it) },
                            onDelete = { viewModel.delete(it) } ,
                            onEdit = {navController.navigate("EditScreen/${task.id}")}
                        )
                        Spacer(modifier = Modifier.height(8.dp))
                    }
                }
            }

        }

    }
}


@Composable
fun TaskCard(
    task: Task,
    onCheckChange: (Task) -> Unit,
    onDelete: (Task) -> Unit,
    onEdit: (taskId: Int) -> Unit
) {
    Card(
        shape = RoundedCornerShape(16.dp),
        elevation = CardDefaults.cardElevation(5.dp) ,
        modifier = Modifier.fillMaxWidth().padding(4.dp) ,
        onClick = {onEdit(task.id)},
        border = BorderStroke(1.dp , NeonGreen)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = task.title,
                    modifier = Modifier.weight(1f)
                )
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Checkbox(
                        checked = task.isCompleted,
                        onCheckedChange = { onCheckChange(task) } ,
                    )
                    IconButton(onClick = { onDelete(task) }) {
                        Icon(
                            imageVector = Icons.Default.Delete,
                            contentDescription = "Delete Task",
                            tint = Color.Red
                        )
                    }
                }
            }
            Spacer(modifier = Modifier.height(8.dp))
            Text(
                text = task.description,
            )
        }
    }
}


