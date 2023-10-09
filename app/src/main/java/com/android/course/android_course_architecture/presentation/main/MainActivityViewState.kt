package com.android.course.android_course_architecture.presentation.main

import com.android.course.android_course_architecture.domain.model.RecipesModel

sealed interface MainActivityViewState {

    data object Loading : MainActivityViewState

    data class Content(val items: List<RecipesModel>) : MainActivityViewState

    data object Error : MainActivityViewState
}