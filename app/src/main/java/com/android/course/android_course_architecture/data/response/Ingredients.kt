package com.android.course.android_course_architecture.data.response

import com.google.gson.annotations.SerializedName

data class Ingredients(

    @SerializedName("text")
    val text: String,

    @SerializedName("image")
    val imageUri: String
)
