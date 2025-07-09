package com.example.miniproyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.miniproyecto.data.ActivityPreferences
import kotlinx.coroutines.launch

@Composable
fun GoalsScreen(navController: NavController) {
    val context = LocalContext.current
    val scope = rememberCoroutineScope()
    var goal by remember { mutableStateOf("") }

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Establecer Meta Diaria", fontSize = 24.sp)
        Spacer(Modifier.height(16.dp))
        OutlinedTextField(
            value = goal,
            onValueChange = { goal = it },
            label = { Text("Meta de pasos") },
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
        )
        Spacer(Modifier.height(16.dp))
        Button(onClick = {
            goal.toIntOrNull()?.let {
                scope.launch {
                    ActivityPreferences.saveStepGoal(context, it)
                    navController.popBackStack()
                }
            }
        }) {
            Text("Guardar Meta")
        }
    }
}
