package com.android.course.android_course_architecture.di

import com.android.course.android_course_architecture.data.RecipeRepository
import com.android.course.android_course_architecture.data.api.RecipesService
import com.android.course.android_course_architecture.data.api.RetrofitClientInstance
import retrofit2.Retrofit

object GlobalDI {

    private val retrofit: Retrofit = RetrofitClientInstance.getInstance()
    private val recipesService: RecipesService = retrofit.create(RecipesService::class.java)
    val recipeRepository: RecipeRepository = RecipeRepository(recipesService)
}