package com.example.todolistapp.Screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.example.todolistapp.MyappViewModel
import com.example.todolistapp.Database.Task
import com.example.todolistapp.ui.theme.GreyBackGround
import com.example.todolistapp.ui.theme.NeonGreen


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun InsertScreen(viewModel : MyappViewModel = hiltViewModel() , navController: NavController){
    var title by remember { mutableStateOf("") }
    var faculty by remember { mutableStateOf("") }
    var description by remember { mutableStateOf("") }
    val scope = rememberCoroutineScope()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(GreyBackGround).padding(top = 10.dp),
        verticalArrangement = Arrangement.Top ,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Title" , color= Color.White) },
            modifier = Modifier.fillMaxWidth().padding(16.dp) ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = NeonGreen,
                unfocusedBorderColor = NeonGreen,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = faculty,
            onValueChange = { faculty = it },
            label = { Text("Faculty") },
            modifier = Modifier.fillMaxWidth().padding(16.dp),
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = NeonGreen,
                unfocusedBorderColor = NeonGreen,
            )
        )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = description,
            onValueChange = { description = it },
            label = { Text("Description") },
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp).padding(16.dp),
            maxLines = 5 ,
            colors = TextFieldDefaults.outlinedTextFieldColors(
                containerColor = Color.Transparent,
                cursorColor = Color.White,
                focusedBorderColor = NeonGreen,
                unfocusedBorderColor = NeonGreen,
            )
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = {
                if (title.isNotBlank() && faculty.isNotBlank() && description.isNotBlank()) {
                    viewModel.insert(Task(title = title, faculty = faculty, description =  description , isCompleted = false))
                    navController.popBackStack()  // Navigate back after saving
                }
            },
            modifier = Modifier.fillMaxWidth().padding(start = 16.dp, end = 16.dp),
            enabled = title.isNotBlank() && faculty.isNotBlank() && description.isNotBlank() ,
            colors = androidx.compose.material3.ButtonDefaults.buttonColors(
                containerColor = NeonGreen
            )
        ) {
            Text("Save Task" , color = Color.White)
        }
    }
}
