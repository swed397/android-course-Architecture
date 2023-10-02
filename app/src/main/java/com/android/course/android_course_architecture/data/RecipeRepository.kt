package com.android.course.android_course_architecture.data

import com.android.course.android_course_architecture.data.api.RecipesService
import com.android.course.android_course_architecture.domain.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(private val recipesService: RecipesService) {

    //ToDo refactor
    suspend fun getAllRecipes(): List<RecipesModel> {
        return withContext(Dispatchers.IO) {
            recipesService.getAllRecipes(query = "salad").hits.map {
                RecipesModel(
                    label = it.label,
                    calories = it.calories,
                    image = null
                )
            }
        }
    }
}