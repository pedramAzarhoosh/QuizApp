package com.example.quizapp.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.RadioButton
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.example.quizapp.R
import com.example.quizapp.databinding.ActivityMathBinding
import com.example.quizapp.model.Questions
import com.example.quizapp.model.QuestionsItem
import com.example.quizapp.viewmodel.QuizViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch

class MathActivity : AppCompatActivity() {
    lateinit var binding:ActivityMathBinding
    lateinit var viewModel: QuizViewModel
    lateinit var questions: Questions
    lateinit var questionItem:QuestionsItem
    lateinit var button:Button

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

        //when you use views use main thread in background
        GlobalScope.launch (Dispatchers.Main){
            viewModel.getAllQuestions().observe(this@MathActivity, Observer {
                if(it.size > 0){
                    questions = it
                    Log.i("TAGGY","This is the first question: $questions[0]")
                    questionItem = QuestionsItem(questions[0].correct_option,questions[0].option1,questions[0].option2,
                        questions[0].option3,questions[0].option4,questions[0].question)
                }
                binding.questionItem = questionItem
            })
        }


        var i = 1
        button = binding.nextBtn
        button.setOnClickListener{
            val selected = binding.radioGroup.checkedRadioButtonId
            if(selected != -1){
                val correctRadio = findViewById<View>(selected) as RadioButton
                questions.let {
                    if(i<it.size){
                        totalQuestions = it.size
                        if(correctRadio.text.toString().equals(it[i-1].correct_option)){
                            result++
                            binding.answersResult.text = "Correct answers : $result"
                        }
                    }

                    questionItem = QuestionsItem(it[i].correct_option,it[i].option1,it[i].option2,
                        it[i].option3,it[i].option4,it[i].question)

                    binding.questionItem = questionItem

                    if(i == it.size.minus(1)){
                        binding.nextBtn.text = "FINISH"
                        val intent = Intent(this@MathActivity,ResultActivity::class.java)
                        startActivity(intent)
                    }

                    binding.radioGroup.clearCheck()
                    i++
                }
            } else{
                Toast.makeText(this@MathActivity,"Please select one choice!",Toast.LENGTH_SHORT).show()
            }
        }

    }
}