package com.android.course.android_course_architecture.domain.model

data class DetailRecipeModel(
    val recipeName: String,
    val ingredients: String,
    val recipeSteps: List<RecipeStep>
)
