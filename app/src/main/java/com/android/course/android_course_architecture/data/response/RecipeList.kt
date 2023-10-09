package com.android.course.android_course_architecture.data.response

import com.google.gson.annotations.SerializedName

data class RecipeList(

    @SerializedName("recipe")
    val recipe: RecipeInfo
)
