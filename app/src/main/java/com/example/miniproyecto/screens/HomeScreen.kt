package com.example.miniproyecto.screens

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.miniproyecto.data.ActivityPreferences
import com.example.miniproyecto.sensors.LocationTracker
import com.example.miniproyecto.sensors.StepCounterManager
import com.example.miniproyecto.utils.estimateCalories
import com.example.miniproyecto.utils.estimateDistance
import com.example.miniproyecto.utils.getMotivationalMessage

@Composable
fun HomeScreen(navController: NavController) {
    val context = LocalContext.current

    val stepCounter = remember { StepCounterManager(context) }
    val locationTracker = remember { LocationTracker(context) }

    val steps by stepCounter.steps.observeAsState(0)
    val distanceMeters by locationTracker.distanceInMeters.observeAsState(0f)
    val distanceKm = distanceMeters / 1000f

    val stepGoal by ActivityPreferences.getStepGoal(context).collectAsState(initial = 8000)
    val progress = if (stepGoal > 0) steps.toFloat() / stepGoal else 0f
    val motivationMessage = getMotivationalMessage(progress)

    val calories = estimateCalories(steps)
    val distance = estimateDistance(steps)

    LaunchedEffect(Unit) {
        stepCounter.start()
        locationTracker.startLocationUpdates()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp)
    ) {
        Text("Resumen Diario", style = MaterialTheme.typography.titleLarge)

        Text("Pasos: $steps")
        Text("Distancia estimada (pasos): %.2f km".format(distance))
        Text("Distancia (GPS): %.2f km".format(distanceKm))
        Text("Calorías estimadas: %.1f kcal".format(calories))
        Text("Meta diaria: $stepGoal pasos")

        LinearProgressIndicator(
            progress = { progress.coerceIn(0f, 1f) },
            modifier = Modifier
                .fillMaxWidth()
                .height(12.dp)
        )

        Text("Progreso: ${(progress * 100).toInt()}%")
        Text(motivationMessage, style = MaterialTheme.typography.bodyLarge)

        Spacer(Modifier.height(24.dp))

        Button(onClick = { navController.navigate("stats") }) {
            Text("Ver Estadísticas")
        }
        Button(onClick = { navController.navigate("goals") }) {
            Text("Establecer Metas")
        }
        Button(onClick = { navController.navigate("history") }) {
            Text("Ver Historial")
        }
        Button(onClick = { navController.navigate("settings") }) {
            Text("Configuración")
        }
    }
}


