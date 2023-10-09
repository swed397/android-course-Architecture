package com.android.course.android_course_architecture.presentation.recipe

import com.android.course.android_course_architecture.domain.model.DetailRecipeModel

sealed interface RecipeFragmentViewState {

    data object Loading : RecipeFragmentViewState

    data class Content(val items: DetailRecipeModel) : RecipeFragmentViewState

    data object Error : RecipeFragmentViewState
}