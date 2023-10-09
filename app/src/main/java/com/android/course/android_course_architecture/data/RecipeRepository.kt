package com.android.course.android_course_architecture.data

import com.android.course.android_course_architecture.data.api.RecipesService
import com.android.course.android_course_architecture.domain.model.DetailRecipeModel
import com.android.course.android_course_architecture.domain.model.RecipeStep
import com.android.course.android_course_architecture.domain.model.RecipesModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RecipeRepository(private val recipesService: RecipesService) {
    suspend fun getAllRecipes(query: String): List<RecipesModel> =
        withContext(Dispatchers.IO) {
            recipesService.getAllRecipes(query = query).hits.map {
                RecipesModel(
                    label = it.recipe.label,
                    calories = it.recipe.calories.toInt(),
                    imageUrl = it.recipe.imagesUri.thumbnail.url,
                    uri = it.recipe.recipeUri
                )
            }
        }

    suspend fun getRecipeByUri(url: String): DetailRecipeModel =
        withContext(Dispatchers.IO) {
            recipesService.getRecipeByUri(uri = listOf(url)).hits[0].run {
                DetailRecipeModel(
                    recipeName = recipe.label,
                    ingredients = recipe.ingredientLines.joinToString(", "),
                    recipeSteps = recipe.ingredients.map {
                        RecipeStep(
                            text = it.text,
                            imageUrl = it.imageUri
                        )
                    }
                )
            }
        }
}

