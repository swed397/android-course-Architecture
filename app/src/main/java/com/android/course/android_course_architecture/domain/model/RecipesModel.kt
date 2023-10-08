package com.android.course.android_course_architecture.domain.model

import android.graphics.Bitmap

data class RecipesModel(
    val label: String,
    val calories: Int,
    val image: Bitmap?,
    val uri: String
)
