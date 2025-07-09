package com.example.miniproyecto.utils

fun getMotivationalMessage(progress: Float): String {
    return when {
        progress >= 1f -> "¡Excelente trabajo, superaste tu meta!"
        progress >= 0.7f -> "¡Muy bien! Sigue así, ya casi llegas."
        progress >= 0.3f -> "¡Buen ritmo! Aún tienes tiempo para lograrlo."
        else -> "¡Vamos, tú puedes hacerlo!"
    }
}