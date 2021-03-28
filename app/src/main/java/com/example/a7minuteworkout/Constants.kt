package com.example.a7minuteworkout

class Constants {

        companion object {
            fun defaultExcerciseList() : ArrayList<ExcerciseModel> {
                val excerciseList = ArrayList<ExcerciseModel>()

                val jumpingJack = ExcerciseModel(
                    1,
                    "Jumping Jacks",
                    R.drawable.ic_jumping_jacks,
                    false,
                    false)

                val abdominalCrunch = ExcerciseModel(
                    2,
                    "Abdominal Crunch",
                    R.drawable.ic_abdominal_crunch,
                    false,
                    false)

                val highKneesRunning = ExcerciseModel(
                    3,
                    "High Knees Running in Place",
                    R.drawable.ic_high_knees_running_in_place,
                    false,
                    false)

                val lunge = ExcerciseModel(
                    4,
                    "Lunge",
                    R.drawable.ic_lunge,
                    false,
                    false)

                val plank = ExcerciseModel(
                    5,
                    "Plang",
                    R.drawable.ic_plank,
                    false,
                    false)

                val pushUp = ExcerciseModel(
                    6,
                    "Push Up",
                    R.drawable.ic_push_up,
                    false,
                    false)

                val pushUpAndRotation = ExcerciseModel(
                    7,
                    "Push Up and Rotation",
                    R.drawable.ic_push_up_and_rotation,
                    false,
                    false)

                val sidePlank = ExcerciseModel(
                    8,
                    "Side Plank",
                    R.drawable.ic_side_plank,
                    false,
                    false)

                val squat = ExcerciseModel(
                    9,
                    "Squat",
                    R.drawable.ic_squat,
                    false,
                    false)

                val stepUpOntoChair = ExcerciseModel(
                    10,
                    "Step Up onto Chair",
                    R.drawable.ic_step_up_onto_chair,
                    false,
                    false)

                val tricepsDipOnChair = ExcerciseModel(
                    11,
                    "Triceps Dip on Chair",
                    R.drawable.ic_triceps_dip_on_chair,
                    false,
                    false)

                val wallSit = ExcerciseModel(
                    12,
                    "Wall Sit",
                    R.drawable.ic_wall_sit,
                    false,
                    false)

                excerciseList.add(jumpingJack)
                excerciseList.add(abdominalCrunch)
                excerciseList.add(highKneesRunning)
                excerciseList.add(lunge)
                excerciseList.add(plank)
                excerciseList.add(pushUp)
                excerciseList.add(pushUpAndRotation)
                excerciseList.add(sidePlank)
                excerciseList.add(squat)
                excerciseList.add(stepUpOntoChair)
                excerciseList.add(tricepsDipOnChair)
                excerciseList.add(wallSit)

                return excerciseList
            }

        }

}