package com.seanpmcb.move.data

import android.content.Context
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor
import java.io.File
import java.io.OutputStreamWriter

class YamlParser(private val context: Context) {
    private val workoutGroupsYaml = Yaml(Constructor(WorkoutGroups::class.java))
    private val workoutYaml = Yaml(Constructor(Workout::class.java))

    fun loadWorkoutGroups(): List<WorkoutGroup> {
        val inputStream = context.assets.open("workouts/workout_groups.yaml")
        val yamlString = inputStream.bufferedReader().use { it.readText() }
        val workoutGroups = workoutGroupsYaml.load<WorkoutGroups>(yamlString)
        return workoutGroups.groups
    }

    fun loadWorkout(filePath: String): Workout {
        val workoutFile = File(context.filesDir, filePath)
        
        // If file exists in internal storage, load from there
        if (workoutFile.exists()) {
            return workoutYaml.load<Workout>(workoutFile.inputStream())
        }
        
        // Otherwise, load from assets and copy to internal storage
        val inputStream = context.assets.open("workouts/$filePath")
        val yamlString = inputStream.bufferedReader().use { it.readText() }
        val workout = workoutYaml.load<Workout>(yamlString)
        
        // Create directory structure and save to internal storage
        workoutFile.parentFile?.mkdirs()
        workoutFile.outputStream().use { outputStream ->
            workoutYaml.dump(workout, OutputStreamWriter(outputStream))
        }
        
        return workout
    }

    @Synchronized
    fun updateExerciseWeight(workoutPath: String, exerciseIndex: Int, newWeight: Int) {
        // Create the directory structure in internal storage
        val workoutFile = File(context.filesDir, workoutPath)
        workoutFile.parentFile?.mkdirs()

        if (!workoutFile.exists()) {
            // Copy from assets to internal storage
            context.assets.open("workouts/$workoutPath").use { input ->
                workoutFile.outputStream().use { output ->
                    input.copyTo(output)
                }
            }
        }

        // Read and update the workout
        val workout = workoutYaml.load<Workout>(workoutFile.inputStream())
        if (exerciseIndex >= 0 && exerciseIndex < workout.exercises.size) {
            workout.exercises[exerciseIndex].weight = newWeight
            
            // Write back to file
            workoutFile.outputStream().use { outputStream ->
                workoutYaml.dump(workout, OutputStreamWriter(outputStream))
            }
        }
    }
} 