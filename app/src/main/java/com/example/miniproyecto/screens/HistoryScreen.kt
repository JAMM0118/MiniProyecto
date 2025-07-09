package com.example.miniproyecto.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.miniproyecto.utils.getLocalHistory

@Composable
fun HistoryScreen(navController: NavController) {
    val history = getLocalHistory()

    Column(Modifier.fillMaxSize().padding(16.dp)) {
        Text("Historial de Actividad", fontSize = 24.sp)
        Spacer(Modifier.height(12.dp))

        history.forEach { entry ->
            Text("📅 ${entry.date}")
            Text("Pasos: ${entry.steps}")
            Text("Distancia: %.2f km".format(entry.distanceKm))
            Text("Calorías: %.1f kcal".format(entry.calories))
            Spacer(Modifier.height(12.dp))
        }
    }
}
