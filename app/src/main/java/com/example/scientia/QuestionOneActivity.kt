package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import com.example.scientia.databinding.ActivityQuestionOneBinding
import com.example.scientia.models.Constants.getMathQuestions
import com.example.scientia.models.MathQuestion
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class QuestionOneActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivityQuestionOneBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Bind the View with This Kotlin File
        binding = ActivityQuestionOneBinding.inflate(layoutInflater)
        setContentView(binding.root);

        //Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Navigate to Category View
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java);
            startActivity(intent);
        }

        //get the question Number from prev activity
        var questionNumber = intent.getIntExtra("questionNumber", 0)

        //All Math Questions
        val questions = getMathQuestions();
        //Select Current Question
        val currentQuestion = questions[questionNumber];

        Log.i("Questions Count:", "${currentQuestion.answers.contains("Four")}");

        //Bind Questions Info to View
        updateUI(currentQuestion);

        binding.acbBtnNext.setOnClickListener {
            //Check to see if this is the last question
            if(questionNumber === questions.count() - 1){
                //Navigate away
                val intent = Intent(this, ResultActivity::class.java);

                startActivity(intent);
                finish();
            } else {
                val intent = Intent(this, QuestionOneActivity::class.java);

                //Pass next Question Value
                intent.putExtra("questionNumber", questionNumber + 1);
                startActivity(intent);
                finish();
            }

        }
    }

    fun updateUI(currentQuestion: MathQuestion) {
        binding.tvQuestionText.text = currentQuestion.question;
    }

}