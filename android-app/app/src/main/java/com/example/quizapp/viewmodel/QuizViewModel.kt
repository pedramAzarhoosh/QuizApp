package com.example.quizapp.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.quizapp.model.Questions
import com.example.quizapp.repository.QuizRepository

class QuizViewModel: ViewModel() {

    var repository = QuizRepository()

    public fun getAllQuestions():MutableLiveData<Questions>{
        return repository.getQuestionsFromApi()
    }


}