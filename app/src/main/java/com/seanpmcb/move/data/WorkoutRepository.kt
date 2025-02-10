package com.seanpmcb.move.data

import java.util.UUID

class WorkoutRepository {
    private val workoutGroups = mutableListOf<WorkoutGroup>()

    init {
        addSampleWorkoutGroups()
    }

    private fun addSampleWorkoutGroups() {
        // Add test workout
        val testWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Quick Test",
            description = "A short workout for testing the app",
            exercises = buildList {
                // Exercise 1
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Test Exercise 1",
                    duration = 5,
                    type = ExerciseType.WORK,
                    instructions = "First test exercise"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 3,
                    type = ExerciseType.REST
                ))

                // Exercise 2
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Test Exercise 2",
                    duration = 5,
                    type = ExerciseType.WORK,
                    instructions = "Second test exercise"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 3,
                    type = ExerciseType.REST
                ))

                // Exercise 3
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Test Exercise 3",
                    duration = 5,
                    type = ExerciseType.WORK,
                    instructions = "Final test exercise"
                ))
            },
            totalDuration = 21 // 3 exercises (5s each) + 2 rests (3s each)
        )

        val testGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Test",
            workouts = listOf(testWorkout)
        )

        // Morning Group with Wake Up workout
        val wakeUpWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake Up",
            description = "A gentle sequence of stretches to start your day",
            exercises = buildList {
                // Exercise 1: Upward Salute
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Upward Salute",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Stand tall, reach arms overhead, palms facing each other"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 2: Toe Touch
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Toe Touch",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Bend forward at hips, reaching for toes"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 3: Lunge Sequence
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Right Lunge",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Step right foot back into a lunge"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Switch Sides",
                    duration = 5,
                    type = ExerciseType.TRANSITION,
                    instructions = "Switch to left side"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Left Lunge",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Step left foot back into a lunge"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 4: Upward Dog
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Upward Dog",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Lie on stomach, push chest up, legs extended"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 5: Child's Pose
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Child's Pose",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Kneel and sit back on heels, extend arms forward"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 6: Downward Dog
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Downward Dog",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Form an inverted V with body, hands and feet pressed into mat"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 7: Wide Leg Bend
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Wide Leg Bend",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Stand with legs wide, fold forward at hips"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // Exercise 8: Final Upward Salute
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Upward Salute",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Stand tall, reach arms overhead, palms facing each other"
                ))
            },
            totalDuration = 275 // Total duration including all exercises and rest periods
        )

        val morningGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Morning",
            workouts = listOf(wakeUpWorkout)
        )

        val quickCoreWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Quick Core",
            description = "A comprehensive core workout targeting all areas",
            exercises = buildList {
                // 1. Hand Plank
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Hand Plank",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Hold high plank position with straight arms and engaged core"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 2. Elbow Plank
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Elbow Plank",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Hold plank position on forearms with engaged core"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 3. Elbow Pike
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Elbow Pike",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "From elbow plank, pike hips up towards ceiling"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 4. Elbow Side Plank Sequence
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Right Side Plank",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Hold side plank on right forearm"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Switch Sides",
                    duration = 5,
                    type = ExerciseType.TRANSITION,
                    instructions = "Switch to left side"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Left Side Plank",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Hold side plank on left forearm"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 5. Reverse Plank
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Reverse Plank",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Hold reverse plank position with straight arms"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 6. Reverse Plank Leg Lift Sequence
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Right Leg Lift",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "In reverse plank, lift right leg"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Switch Sides",
                    duration = 5,
                    type = ExerciseType.TRANSITION,
                    instructions = "Switch to left leg"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Left Leg Lift",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "In reverse plank, lift left leg"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 7. Bridge
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Bridge",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Lift hips into bridge position, squeeze glutes"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 8. Leg Lift
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Leg Lift",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Lying on back, lift both legs to 90 degrees"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 9. Toe Touch Hold
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Toe Touch Hold",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Reach for toes with legs at 90 degrees"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 10. Bicycle Crunch Hold Sequence
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Right Bicycle Hold",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Hold bicycle crunch with right elbow to left knee"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Switch Sides",
                    duration = 5,
                    type = ExerciseType.TRANSITION,
                    instructions = "Switch to other side"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Left Bicycle Hold",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Hold bicycle crunch with left elbow to right knee"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 11. Lying Side Leg Raise Sequence
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Right Side Leg Raise",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Lying on right side, lift left leg"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Switch Sides",
                    duration = 5,
                    type = ExerciseType.TRANSITION,
                    instructions = "Switch to other side"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Left Side Leg Raise",
                    duration = 15,
                    type = ExerciseType.WORK,
                    instructions = "Lying on left side, lift right leg"
                ))
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Rest",
                    duration = 5,
                    type = ExerciseType.REST
                ))

                // 12. Superman
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Superman",
                    duration = 30,
                    type = ExerciseType.WORK,
                    instructions = "Lying face down, lift arms and legs off ground"
                ))
            },
            totalDuration = 420 // Total duration including all exercises and rest periods
        )

        val coreGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Core",
            workouts = listOf(quickCoreWorkout)
        )

        workoutGroups.addAll(listOf(testGroup, morningGroup, coreGroup))
    }

    fun getAllWorkoutGroups(): List<WorkoutGroup> = workoutGroups.toList()
    
    fun getWorkoutGroupById(id: String): WorkoutGroup? = workoutGroups.find { it.id == id }
    
    fun getWorkoutById(id: String): Workout? = workoutGroups
        .flatMap { it.workouts }
        .find { it.id == id }
} 