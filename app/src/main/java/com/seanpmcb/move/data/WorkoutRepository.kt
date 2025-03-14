package com.seanpmcb.move.data

import java.util.UUID
import com.seanpmcb.move.R

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
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Exercise 1
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Test Exercise 1",
                            duration = 5,
                            type = ExerciseType.WORK,
                            instructions = "First test exercise",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 3,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Exercise 2
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Test Exercise 2",
                            duration = 5,
                            type = ExerciseType.WORK,
                            instructions = "Second test exercise",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 3,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Exercise 3
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Test Exercise 3",
                            duration = 5,
                            type = ExerciseType.WORK,
                            instructions = "Final test exercise",
                            imageResId = null
                        ))
                    },
                    repetitions = 2,
                    restBetweenRepetitions = 7
                )
            ),
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
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Exercise 1: Upward Salute
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Upward Salute",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall, reach arms overhead, palms facing each other",
                            imageResId = R.drawable.upward_salute
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.toe_touch
                        ))

                        // Exercise 2: Toe Touch
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Toe Touch",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Bend forward at hips, reaching for toes",
                            imageResId = R.drawable.toe_touch
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.lunge
                        ))

                        // Exercise 3: Lunge Sequence
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Right Lunge",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Step right foot back into a lunge",
                            imageResId = R.drawable.lunge
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = R.drawable.lunge
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Left Lunge",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Step left foot back into a lunge",
                            imageResId = R.drawable.lunge
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.upward_dog
                        ))

                        // Exercise 4: Upward Dog
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Upward Dog",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Lie on stomach, push chest up, legs extended",
                            imageResId = R.drawable.upward_dog
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.childs_pose
                        ))

                        // Exercise 5: Child's Pose
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Child's Pose",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Kneel and sit back on heels, extend arms forward",
                            imageResId = R.drawable.childs_pose
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.downward_dog
                        ))

                        // Exercise 6: Downward Dog
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Downward Dog",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Form an inverted V with body, hands and feet pressed into mat",
                            imageResId = R.drawable.downward_dog
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.wide_leg_bend
                        ))

                        // Exercise 7: Wide Leg Bend
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Wide Leg Bend",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand with legs wide, fold forward at hips",
                            imageResId = R.drawable.wide_leg_bend
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = R.drawable.upward_salute
                        ))

                        // Exercise 8: Final Upward Salute
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Upward Salute",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall, reach arms overhead, palms facing each other",
                            imageResId = R.drawable.upward_salute
                        ))
                    },
                    repetitions = 1,
                    restBetweenRepetitions = 0
                )
            ),
            totalDuration = 300 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 1 workout
        val wakeAndShakeWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 1",
            description = "A dynamic workout to wake up your body",
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Dynamic Side Bends
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Side Bends",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and bend side to side dynamically.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make small circles with your arms.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your arm circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Swings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Swings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Swing your arms forward and backward.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Good Mornings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Good Mornings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and hinge at the hips, keeping your back straight.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Trunk Twists
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Trunk Twists",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and twist your torso side to side.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Neck Roll
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Neck Roll",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Gently roll your neck in a circular motion.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Hip Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hip Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your hips.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your hip circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Dynamic Wide Leg Bend
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Wide Leg Bend",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand with legs wide and bend forward at the hips.",
                            imageResId = null
                        ))
                    },
                    repetitions = 1,
                    restBetweenRepetitions = 0
                )
            ),
            totalDuration = 300 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 2 workout
        val wakeAndShake2Workout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 2",
            description = "A dynamic workout to wake up your body",
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Dynamic Side Bends
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Side Bends",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and bend side to side dynamically.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make small circles with your arms.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your arm circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Swings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Swings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Swing your arms forward and backward.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Good Mornings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Good Mornings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and hinge at the hips, keeping your back straight.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Trunk Twists
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Trunk Twists",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and twist your torso side to side.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Neck Roll
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Neck Roll",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Gently roll your neck in a circular motion.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Hip Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hip Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your hips.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your hip circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Hamstring Scoops
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hamstring Scoops",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Reach down towards your toes, alternating legs.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hamstring Scoops",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Continue with the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Gate Openers
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Gate Openers",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Open and close your legs like a gate.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Gate Openers",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Switch to the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Dynamic Wide Leg Forward Fold
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Wide Leg Forward Fold",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand with legs wide and fold forward at the hips.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Wide Leg Torso Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Wide Leg Torso Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your torso while standing wide leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your torso circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Knee Hugs
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Knee Hugs",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Hug your knees to your chest, alternating legs.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Knee Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Knee Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your knees.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your knee circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))
                    },
                    repetitions = 1,
                    restBetweenRepetitions = 0
                )
            ),
            totalDuration = 420 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 3 workout
        val wakeAndShake3Workout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 3",
            description = "A dynamic workout to wake up your body",
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Dynamic Side Bends
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Side Bends",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and bend side to side dynamically.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make small circles with your arms.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your arm circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Arm Swings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Arm Swings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Swing your arms forward and backward.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Good Mornings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Good Mornings",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and hinge at the hips, keeping your back straight.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Trunk Twists
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Trunk Twists",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand tall and twist your torso side to side.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Shoulder Rolls
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Shoulder Rolls",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Roll your shoulders forward and then backward.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Neck Rolls
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Neck Rolls",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Gently roll your neck in a circular motion.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Hip Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hip Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your hips.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your hip circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Hamstring Scoops
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hamstring Scoops",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Reach down towards your toes, alternating legs.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hamstring Scoops",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Continue with the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Gate Openers
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Gate Openers",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Open and close your legs like a gate.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Gate Openers",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Switch to the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Leg Swings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Leg Swings",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Swing your leg forward and backward.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to other side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Leg Swings",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Switch to the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Lateral Leg Swings
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Lateral Leg Swings",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Swing your leg side to side.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to other side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Lateral Leg Swings",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Switch to the other leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Dynamic Wide Leg Forward Fold
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Dynamic Wide Leg Forward Fold",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Stand with legs wide and fold forward at the hips.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Wide Leg Torso Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Wide Leg Torso Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your torso while standing wide leg.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your torso circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Toe Touch Twist
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Toe Touch Twist",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Touch your toes while twisting your torso.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Standing Hip Openers
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Standing Hip Openers",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Open your hips while standing, alternating legs.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // High Knee Twists
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "High Knee Twists",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Lift your knees high while twisting your torso.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Knee Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Knee Circles",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with your knees.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your knee circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // Ankle Circles
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Ankle Circles",
                            duration = 10,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with one ankle.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 10,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your ankle circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to other side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Ankle Circles",
                            duration = 10,
                            type = ExerciseType.WORK,
                            instructions = "Make circles with the other ankle.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Change Direction",
                            duration = 10,
                            type = ExerciseType.WORK,
                            instructions = "Change the direction of your ankle circles.",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))
                    },
                    repetitions = 1,
                    restBetweenRepetitions = 0
                )
            ),
            totalDuration = 480 // Total duration including all exercises and rest periods
        )

        // Add the new workout to the Morning group
        val morningGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Morning",
            workouts = listOf(wakeUpWorkout, wakeAndShakeWorkout, wakeAndShake2Workout, wakeAndShake3Workout) // Include the new workout
        )

        val quickCoreWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Quick Core",
            description = "A comprehensive core workout targeting all areas",
            exerciseSets = listOf(
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // 1. Hand Plank
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Hand Plank",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Hold high plank position with straight arms and engaged core",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 2. Elbow Plank
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Elbow Plank",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Hold plank position on forearms with engaged core",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 3. Elbow Pike
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Elbow Pike",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "From elbow plank, pike hips up towards ceiling",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 4. Elbow Side Plank Sequence
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Right Side Plank",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Hold side plank on right forearm",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Left Side Plank",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Hold side plank on left forearm",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 5. Reverse Plank
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Reverse Plank",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Hold reverse plank position with straight arms",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 6. Reverse Plank Leg Lift Sequence
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Right Leg Lift",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "In reverse plank, lift right leg",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to left leg",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Left Leg Lift",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "In reverse plank, lift left leg",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 7. Bridge
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Bridge",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Lift hips into bridge position, squeeze glutes",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 8. Leg Lift
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Leg Lift",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Lying on back, lift both legs to 90 degrees",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 9. Toe Touch Hold
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Toe Touch Hold",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Reach for toes with legs at 90 degrees",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 10. Bicycle Crunch Hold Sequence
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Right Bicycle Hold",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Hold bicycle crunch with right elbow to left knee",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to other side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Left Bicycle Hold",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Hold bicycle crunch with left elbow to right knee",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 11. Lying Side Leg Raise Sequence
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Right Side Leg Raise",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Lying on right side, lift left leg",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Switch Sides",
                            duration = 5,
                            type = ExerciseType.TRANSITION,
                            instructions = "Switch to other side",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Left Side Leg Raise",
                            duration = 15,
                            type = ExerciseType.WORK,
                            instructions = "Lying on left side, lift right leg",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 5,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))

                        // 12. Superman
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Superman",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Lying face down, lift arms and legs off ground",
                            imageResId = null
                        ))
                    },
                    repetitions = 1,
                    restBetweenRepetitions = 0
                )
            ),
            totalDuration = 420 // Total duration including all exercises and rest periods
        )

        val coreGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Core",
            workouts = listOf(quickCoreWorkout)
        )

        // Add a new workout with multiple sets as an example
        val multiSetWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Multi-Set Example",
            description = "A workout demonstrating multiple sets with repetitions",
            exerciseSets = listOf(
                // First set - Push-ups and Squats, 3 repetitions with 30s rest between
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Push-ups
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Push-ups",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Do push-ups with proper form",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 15,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))
                        
                        // Squats
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Squats",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Do squats with proper form",
                            imageResId = null
                        ))
                    },
                    repetitions = 3,
                    restBetweenRepetitions = 30
                ),
                
                // Second set - Planks and Mountain Climbers, 2 repetitions with 20s rest between
                ExerciseSet(
                    id = UUID.randomUUID().toString(),
                    exercises = buildList {
                        // Plank
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Plank",
                            duration = 45,
                            type = ExerciseType.WORK,
                            instructions = "Hold a plank position",
                            imageResId = null
                        ))
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Rest",
                            duration = 15,
                            type = ExerciseType.REST,
                            imageResId = null
                        ))
                        
                        // Mountain Climbers
                        add(Exercise(
                            id = UUID.randomUUID().toString(),
                            name = "Mountain Climbers",
                            duration = 30,
                            type = ExerciseType.WORK,
                            instructions = "Do mountain climbers at a steady pace",
                            imageResId = null
                        ))
                    },
                    repetitions = 2,
                    restBetweenRepetitions = 20
                )
            ),
            totalDuration = 435 // Calculated total duration including all sets, repetitions, and rests
        )

        // Add the multi-set workout to the test group
        val testGroupWithMultiSet = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Test with Sets",
            workouts = listOf(testWorkout, multiSetWorkout)
        )

        workoutGroups.addAll(listOf(testGroupWithMultiSet, morningGroup, coreGroup))
    }

    fun getAllWorkoutGroups(): List<WorkoutGroup> = workoutGroups.toList()
//
//    fun getWorkoutGroupById(id: String): WorkoutGroup? = workoutGroups.find { it.id == id }
//
//    fun getWorkoutById(id: String): Workout? = workoutGroups
//        .flatMap { it.workouts }
//        .find { it.id == id }
}