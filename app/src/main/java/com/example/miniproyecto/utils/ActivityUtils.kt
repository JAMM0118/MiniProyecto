package com.example.miniproyecto.utils

data class ActivityData(
    val date: String,
    val steps: Int,
    val distanceKm: Double,
    val calories: Double
)

fun estimateDistance(steps: Int): Double {
    val stepLengthMeters = 0.75
    return (steps * stepLengthMeters) / 1000
}

fun estimateCalories(steps: Int): Double {
    val caloriesPerStep = 0.04
    return steps * caloriesPerStep
}