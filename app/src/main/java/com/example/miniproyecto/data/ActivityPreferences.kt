package com.example.miniproyecto.data

import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.doublePreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.miniproyecto.utils.ActivityData
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import java.time.LocalDate

object ActivityPreferences {
    private val Context.dataStore by preferencesDataStore(name = "activity_data")

    val STEPS = intPreferencesKey("steps")
    val DISTANCE = doublePreferencesKey("distance")
    val CALORIES = doublePreferencesKey("calories")

    suspend fun saveActivityData(context: Context, steps: Int, distance: Double, calories: Double) {
        context.dataStore.edit { prefs ->
            prefs[STEPS] = steps
            prefs[DISTANCE] = distance
            prefs[CALORIES] = calories
        }
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun getActivityData(context: Context): Flow<ActivityData> {
        return context.dataStore.data.map { prefs ->
            ActivityData(
                date = LocalDate.now().toString(),
                steps = prefs[STEPS] ?: 0,
                distanceKm = prefs[DISTANCE] ?: 0.0,
                calories = prefs[CALORIES] ?: 0.0
            )
        }
    }
    val STEP_GOAL = intPreferencesKey("step_goal")

    suspend fun saveStepGoal(context: Context, goal: Int) {
        context.dataStore.edit { prefs ->
            prefs[STEP_GOAL] = goal
        }
    }

    fun getStepGoal(context: Context): Flow<Int> {
        return context.dataStore.data.map { prefs ->
            prefs[STEP_GOAL] ?: 8000 // valor por defecto
        }
    }

}


