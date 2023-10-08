package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class RecipeImages(

    @SerializedName("THUMBNAIL")
    val thumbnail: RecipeThumbnailUrl,

    @SerializedName("REGULAR")
    val regular: RecipeThumbnailUrl
)
