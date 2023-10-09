package com.android.course.android_course_architecture.domain.model

data class RecipesModel(
    val label: String,
    val calories: Int,
    val imageUrl: String?,
    val uri: String
)
