package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class RecipeImagesList(

    @SerializedName("THUMBNAIL")
    val thumbnail: List<RecipeThumbnailUrl>
)
