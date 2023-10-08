package com.android.course.android_course_architecture.domain.model.response

import com.google.gson.annotations.SerializedName

data class Ingredients(

    @SerializedName("text")
    val text: String,

    @SerializedName("Image")
    val imageUri: String
)
