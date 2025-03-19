package com.seanpmcb.move.ui.workout

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.data.WorkoutGroup
import com.seanpmcb.move.data.YamlParser
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private lateinit var yamlParser: YamlParser
    
    private val _workoutGroups = MutableStateFlow<List<WorkoutGroup>>(emptyList())
    val workoutGroups: StateFlow<List<WorkoutGroup>> = _workoutGroups.asStateFlow()
    
    private val _selectedWorkout = MutableStateFlow<Workout?>(null)
    val selectedWorkout: StateFlow<Workout?> = _selectedWorkout.asStateFlow()
    
    private val _isLoading = MutableStateFlow(false)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private val _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    fun initialize(context: Context) {
        yamlParser = YamlParser(context)
        loadWorkoutGroups()
    }
    
    private fun loadWorkoutGroups() {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                _workoutGroups.value = yamlParser.loadWorkoutGroups()
            } catch (e: Exception) {
                _error.value = "Failed to load workout groups: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun loadWorkout(workoutId: String) {
        viewModelScope.launch {
            try {
                _isLoading.value = true
                _error.value = null
                
                // Find the workout in the groups
                val workout = _workoutGroups.value
                    .flatMap { it.workouts }
                    .find { it.id == workoutId }
                
                if (workout != null) {
                    _selectedWorkout.value = yamlParser.loadWorkout(workout.file)
                } else {
                    _error.value = "Workout not found: $workoutId"
                }
            } catch (e: Exception) {
                _error.value = "Failed to load workout: ${e.message}"
            } finally {
                _isLoading.value = false
            }
        }
    }
    
    fun clearSelectedWorkout() {
        _selectedWorkout.value = null
    }
} 