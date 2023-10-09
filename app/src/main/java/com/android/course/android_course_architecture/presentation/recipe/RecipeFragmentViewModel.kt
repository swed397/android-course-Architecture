package com.android.course.android_course_architecture.presentation.recipe

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.course.android_course_architecture.data.RecipeRepository
import com.android.course.android_course_architecture.di.GlobalDI
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

class RecipeFragmentViewModel : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val recipeRepository: RecipeRepository = GlobalDI.recipeRepository
    val state: LiveData<RecipeFragmentViewState>
        get() = _state
    private val _state: MutableLiveData<RecipeFragmentViewState> = MutableLiveData()

    fun getRecipeByUrl(url: String) {
        scope.launch {
            _state.postValue(RecipeFragmentViewState.Loading)
            try {
                val result = recipeRepository.getRecipeByUri(url)
                _state.postValue(
                    RecipeFragmentViewState.Content(result)
                )
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                Log.d("Recipe error", e.stackTraceToString())
                _state.postValue(RecipeFragmentViewState.Error)
            }
        }
    }
}