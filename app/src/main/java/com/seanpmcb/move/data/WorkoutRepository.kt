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
            totalDuration = 275 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 1 workout
        val wakeAndShakeWorkout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 1",
            description = "A dynamic workout to wake up your body",
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
            totalDuration = 300 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 2 workout
        val wakeAndShake2Workout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 2",
            description = "A dynamic workout to wake up your body",
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
            totalDuration = 420 // Total duration including all exercises and rest periods
        )

        // New Wake & Shake 3 workout
        val wakeAndShake3Workout = Workout(
            id = UUID.randomUUID().toString(),
            name = "Wake & Shake 3",
            description = "A dynamic workout to wake up your body",
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
            totalDuration = 420 // Total duration including all exercises and rest periods
        )

        val coreGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Core",
            workouts = listOf(quickCoreWorkout)
        )

        // Add Strength Training group
        val strengthTraining1 = Workout(
            id = UUID.randomUUID().toString(),
            name = "Strength Training 1",
            description = "A comprehensive strength training workout focusing on major muscle groups",
            exercises = buildList {
                // First superset: Lateral Lunge and Dumbbell Fly
                repeat(3) { setIndex ->
                    // Lateral Lunge
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Lateral Lunge",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "25 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 25 lbs"
                    ))
                    
                    // Dumbbell Fly
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Dumbbell Fly",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "30 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 30 lbs"
                    ))
                }

                // Second superset: Straight Leg Dead Lift and Pull Ups
                repeat(3) { setIndex ->
                    // Straight Leg Dead Lift
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Straight Leg Dead Lift",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "50 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 50 lbs"
                    ))
                    
                    // Pull Ups
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Pull Ups",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "65 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 65 lbs"
                    ))
                }

                // Hip Tubing (3 rounds)
                add(Exercise(
                    id = UUID.randomUUID().toString(),
                    name = "Hip Tubing",
                    type = ExerciseType.WORK,
                    measurementType = ExerciseMeasurementType.CUSTOM,
                    weight = "65 lbs",
                    customMeasurement = "3 rounds",
                    instructions = "Complete 3 rounds at 65 lbs"
                ))

                // Third superset: Tubing Row and Calf Raise
                repeat(3) { setIndex ->
                    // Tubing Row
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Tubing Row",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "75 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 75 lbs"
                    ))
                    
                    // Calf Raise
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Calf Raise",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "67.5 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 67.5 lbs"
                    ))
                }

                // Fourth superset: Shoulder Press and Seated Toe Raise
                repeat(3) { setIndex ->
                    // Shoulder Press
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Shoulder Press",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "25 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 25 lbs"
                    ))
                    
                    // Seated Toe Raise
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Seated Toe Raise",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "25 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 25 lbs"
                    ))
                }

                // Fifth superset: Dumbbell Curl and Tricep Kickback
                repeat(3) { setIndex ->
                    // Dumbbell Curl
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Dumbbell Curl",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "30 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 30 lbs"
                    ))
                    
                    // Tricep Kickback
                    add(Exercise(
                        id = UUID.randomUUID().toString(),
                        name = "Tricep Kickback",
                        type = ExerciseType.WORK,
                        measurementType = ExerciseMeasurementType.REPS,
                        repetitions = 12,
                        weight = "35 lbs",
                        instructions = "Set ${setIndex + 1}/3: 12 reps at 35 lbs"
                    ))
                }
            },
            totalDuration = 0  // Duration not applicable for rep-based workout
        )

        val strengthTrainingGroup = WorkoutGroup(
            id = UUID.randomUUID().toString(),
            name = "Strength Training",
            workouts = listOf(strengthTraining1)
        )

        workoutGroups.addAll(listOf(
            testGroup,
            morningGroup,
            coreGroup,
            strengthTrainingGroup  // Add the new strength training group
        ))
    }

    fun getAllWorkoutGroups(): List<WorkoutGroup> = workoutGroups.toList()
//
//    fun getWorkoutGroupById(id: String): WorkoutGroup? = workoutGroups.find { it.id == id }
//
//    fun getWorkoutById(id: String): Workout? = workoutGroups
//        .flatMap { it.workouts }
//        .find { it.id == id }
}