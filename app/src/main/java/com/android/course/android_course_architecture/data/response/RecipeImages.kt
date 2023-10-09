package com.android.course.android_course_architecture.data.response

import com.google.gson.annotations.SerializedName

data class RecipeImages(

    @SerializedName("THUMBNAIL")
    val thumbnail: RecipeThumbnailUrl,

    @SerializedName("REGULAR")
    val regular: RecipeThumbnailUrl
)
