package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class RecipeThumbnailUrl(

    @SerializedName("url")
    val uri: String
)
