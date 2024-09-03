package com.example.quizapp.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMathBinding
import com.example.quizapp.model.Questions
import com.example.quizapp.viewmodel.QuizViewModel

class MathActivity : AppCompatActivity() {
    lateinit var binding:ActivityMathBinding
    lateinit var viewModel: QuizViewModel
    lateinit var questions: List<Questions>

    companion object{
        var result = 0
        var totalQuestions = 0
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = DataBindingUtil.setContentView(this,R.layout.activity_math)

        result = 0
        totalQuestions = 0

        viewModel = ViewModelProvider(this).get(QuizViewModel::class.java)
    }
}