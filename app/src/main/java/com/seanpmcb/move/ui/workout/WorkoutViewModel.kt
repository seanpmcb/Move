package com.seanpmcb.move.ui.workout

import android.content.Context
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.seanpmcb.move.data.Workout
import com.seanpmcb.move.data.WorkoutGroup
import com.seanpmcb.move.data.WorkoutRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class WorkoutViewModel : ViewModel() {
    private var _workoutGroups = MutableStateFlow<List<WorkoutGroup>>(emptyList())
    val workoutGroups: StateFlow<List<WorkoutGroup>> = _workoutGroups.asStateFlow()
    
    private var _selectedWorkout = MutableStateFlow<Workout?>(null)
    val selectedWorkout: StateFlow<Workout?> = _selectedWorkout.asStateFlow()
    
    private var _isLoading = MutableStateFlow(true)
    val isLoading: StateFlow<Boolean> = _isLoading.asStateFlow()
    
    private var _error = MutableStateFlow<String?>(null)
    val error: StateFlow<String?> = _error.asStateFlow()
    
    private lateinit var workoutRepository: WorkoutRepository
    private var currentWorkoutId: String? = null
    
    fun initialize(context: Context) {
        workoutRepository = WorkoutRepository(context)
        viewModelScope.launch {
            try {
                workoutRepository.getWorkoutGroups().collect { groups ->
                    _workoutGroups.value = groups
                    _isLoading.value = false
                }
            } catch (e: Exception) {
                _error.value = "Failed to load workouts: ${e.message}"
                _isLoading.value = false
            }
        }
    }
    
    fun loadWorkout(workoutId: String) {
        currentWorkoutId = workoutId
        viewModelScope.launch {
            try {
                workoutRepository.getWorkout(workoutId).collect { workout ->
                    _selectedWorkout.value = workout
                }
            } catch (e: Exception) {
                _error.value = "Failed to load workout: ${e.message}"
            }
        }
    }
    
    fun clearSelectedWorkout() {
        _selectedWorkout.value = null
        currentWorkoutId = null
    }
    
    fun updateExerciseWeight(exerciseIndex: Int, newWeight: Int) {
        viewModelScope.launch {
            try {
                currentWorkoutId?.let { workoutId ->
                    workoutRepository.updateExerciseWeight(workoutId, exerciseIndex, newWeight)
                }
            } catch (e: Exception) {
                _error.value = "Failed to update weight: ${e.message}"
            }
        }
    }
} 