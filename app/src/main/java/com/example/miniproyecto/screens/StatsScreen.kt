package com.example.miniproyecto.screens

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.foundation.layout.*
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.patrykandpatrick.vico.compose.chart.Chart
import com.patrykandpatrick.vico.compose.chart.line.lineChart
import com.patrykandpatrick.vico.core.entry.entryModelOf
import com.patrykandpatrick.vico.core.entry.FloatEntry

@Composable
fun StatsScreen(navController: NavController) {
    val stepsHistory = listOf(1200, 3000, 5000, 4200, 6200, 3100, 4500)
    val days = listOf("Lun", "Mar", "Mié", "Jue", "Vie", "Sáb", "Dom")

    val entries = stepsHistory.mapIndexed { index, steps ->
        FloatEntry(index.toFloat(), steps.toFloat())
    }
    val model = entryModelOf(entries)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Estadísticas Semanales",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(bottom = 24.dp)
        )

        Chart(
            chart = lineChart(),
            model = model,
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
        )

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Resumen por día",
            style = MaterialTheme.typography.titleMedium,
            modifier = Modifier.padding(bottom = 8.dp)
        )

        Column(modifier = Modifier.fillMaxWidth()) {
            days.zip(stepsHistory).forEach { (day, steps) ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 4.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = day, fontSize = 16.sp)
                    Text(text = "$steps pasos", fontSize = 16.sp)
                }
            }
        }
    }
}
