package com.example.quizapp

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.example.quizapp.view.MathActivity

class MainActivity : AppCompatActivity() {
    lateinit var button:Button
    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        button = findViewById(R.id.goToMath)

        button.setOnClickListener {
            val intent = Intent(this,MathActivity::class.java)
            startActivity(intent)
        }
    }
}