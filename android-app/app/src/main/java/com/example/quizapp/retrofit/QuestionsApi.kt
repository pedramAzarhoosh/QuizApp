package com.example.quizapp.retrofit

import com.example.quizapp.model.Questions
import retrofit2.Response
import retrofit2.http.GET

interface QuestionsApi {

    @GET("quizApi.php")
    suspend fun getQuestions(): Response<Questions>
}