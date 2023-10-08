package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class RecipeInfo(

    @SerializedName("uri")
    val recipeUri: String,

    @SerializedName("label")
    val label: String,

    @SerializedName("calories")
    val calories: Double,

    @SerializedName("ingredientLines")
    val ingredientLines: List<String>,

    @SerializedName("ingredients")
    val ingredients: List<Ingredients>,

    @SerializedName("images")
    val imagesUri: RecipeImages
)
