package com.example.quizapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.quizapp.model.Questions
import com.example.quizapp.retrofit.QuestionsApi
import com.example.quizapp.retrofit.RetrofitInstance
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class QuizRepository {
    var questionApi:QuestionsApi

    init {
        questionApi = RetrofitInstance().getRetrofitInstance().create(QuestionsApi::class.java)
    }

    fun getQuestionsFromApi():MutableLiveData<Questions>{
        var data = MutableLiveData<Questions>()
        var questions: Questions

        GlobalScope.launch(Dispatchers.IO) {
            val response = questionApi.getQuestions()

            if(response != null){
                questions = response.body()!!
                data.postValue(questions)
            }
        }
        return data
    }
}