package com.android.course.android_course_architecture.presentation

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.course.android_course_architecture.data.RecipeRepository
import com.android.course.android_course_architecture.di.GlobalDI
import com.android.course.android_course_architecture.domain.model.RecipesModel

class MainActivityViewModel : ViewModel() {

    private val recipeRepository: RecipeRepository = GlobalDI.recipeRepository
    val progressBarState = MutableLiveData<Boolean>()
    val adapterData = MutableLiveData<List<RecipesModel>>()

    suspend fun getAllRecipes(query: String, errorHandle: () -> Unit) {

        try {
            progressBarState.postValue(true)
            val result = recipeRepository.getAllRecipes(query)
            adapterData.postValue(result)
            progressBarState.postValue(false)
        } catch (e: Exception) {
            errorHandle.invoke()
        }
    }
}