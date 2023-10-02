package com.android.course.android_course_architecture.data.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitClientInstance {

    private const val BASE_URL = "https://api.edamam.com/api/"
    const val APP_ID = "9f94cbf7"
    const val TYPE = "public"
    const val APP_KEY = "7c160e78d64f4bccfa388e02cba80f17"

    fun getInstance(): Retrofit =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
}