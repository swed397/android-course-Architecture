package com.android.course.android_course_architecture.data

import android.graphics.Bitmap
import com.android.course.android_course_architecture.data.api.RecipesService
import com.android.course.android_course_architecture.domain.model.RecipesModel
import com.squareup.picasso.Picasso
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

class RecipeRepository(private val recipesService: RecipesService) {
    suspend fun getAllRecipes(query: String): List<RecipesModel> {
        return withContext(Dispatchers.IO) {
            recipesService.getAllRecipes(query = query).hits.map {
                RecipesModel(
                    label = it.recipe.label,
                    calories = it.recipe.calories.toInt(),
                    image = async { getImageByUrl(it.recipe.imagesUri.thumbnail.url) }.await()
                )
            }
        }
    }

    private fun getImageByUrl(url: String): Bitmap = Picasso.get().load(url).get()
}