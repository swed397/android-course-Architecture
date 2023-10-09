package com.android.course.android_course_architecture.presentation.main

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.android.course.android_course_architecture.data.RecipeRepository
import com.android.course.android_course_architecture.di.GlobalDI
import kotlinx.coroutines.CancellationException
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class MainActivityViewModel : ViewModel() {

    private val scope: CoroutineScope = CoroutineScope(SupervisorJob() + Dispatchers.Main)
    private val recipeRepository: RecipeRepository = GlobalDI.recipeRepository
    val state: LiveData<MainActivityViewState>
        get() = _state
    private val _state: MutableLiveData<MainActivityViewState> = MutableLiveData()

    fun getAllRecipes(query: String) {
        scope.launch {
            _state.postValue(MainActivityViewState.Loading)
            try {
                val result = recipeRepository.getAllRecipes(query)
                _state.postValue(MainActivityViewState.Content(result))
            } catch (e: CancellationException) {
                throw e
            } catch (e: Exception) {
                _state.postValue(MainActivityViewState.Error)
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }
}