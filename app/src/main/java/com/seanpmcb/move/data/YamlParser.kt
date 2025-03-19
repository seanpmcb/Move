package com.seanpmcb.move.data

import android.content.Context
import org.yaml.snakeyaml.Yaml
import org.yaml.snakeyaml.constructor.Constructor

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
        val inputStream = context.assets.open("workouts/$filePath")
        val yamlString = inputStream.bufferedReader().use { it.readText() }
        return workoutYaml.load(yamlString)
    }
} 