package com.android.course.android_course_architecture.data.response

import com.google.gson.annotations.SerializedName

data class RecipeResponse(

    @SerializedName("hits")
    val hits: List<RecipeList>
)
