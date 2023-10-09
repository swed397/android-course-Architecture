package com.android.course.android_course_architecture.data.api

import com.android.course.android_course_architecture.data.api.RetrofitClientInstance.APP_ID
import com.android.course.android_course_architecture.data.api.RetrofitClientInstance.APP_KEY
import com.android.course.android_course_architecture.data.api.RetrofitClientInstance.TYPE
import com.android.course.android_course_architecture.data.response.RecipeResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface RecipesService {

    @GET("recipes/v2")
    suspend fun getAllRecipes(
        @Query("app_id") appId: String = APP_ID,
        @Query("type") type: String = TYPE,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("q") query: String
    ): RecipeResponse

    @GET("recipes/v2/by-uri")
    suspend fun getRecipeByUri(
        @Query("app_id") appId: String = APP_ID,
        @Query("type") type: String = TYPE,
        @Query("app_key") appKey: String = APP_KEY,
        @Query("uri") uri: List<String>
    ): RecipeResponse
}