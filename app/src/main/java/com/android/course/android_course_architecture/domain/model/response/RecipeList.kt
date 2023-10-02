package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class RecipeList(

    @SerializedName("label")
    val label: String,

    @SerializedName("calories")
    val calories: Double,

    @SerializedName("images")
    val imagesUri: List<RecipeImagesList>
)
