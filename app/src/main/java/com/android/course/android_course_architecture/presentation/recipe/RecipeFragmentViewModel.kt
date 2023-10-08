package com.android.course.android_course_architecture.presentation.recipe

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.course.android_course_architecture.di.GlobalDI
import com.android.course.android_course_architecture.domain.model.RecipeStep

class RecipeFragmentViewModel : ViewModel() {

    private val recipeRepository = GlobalDI.recipeRepository
    val progressBarState = MutableLiveData<Boolean>()
    val recipesSteps = MutableLiveData<List<RecipeStep>>()

    suspend fun getRecipeByUrl(uri: String, errorHandle: () -> Unit) {
        try {
            progressBarState.postValue(true)
            val result = recipeRepository.getRecipeByUri(uri).recipeSteps
            recipesSteps.postValue(result)
            progressBarState.postValue(false)
        } catch (e: Exception) {
            errorHandle.invoke()
        }
    }

}