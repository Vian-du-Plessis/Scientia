@file:Suppress("DEPRECATION")

package com.example.scientia

import android.content.res.ColorStateList
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Window
import android.view.WindowManager
import android.widget.RadioButton
import android.widget.RadioGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.cardview.widget.CardView
import com.example.scientia.databinding.ActivityCategoryBinding
import com.example.scientia.databinding.ActivityQuestionTwoBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class QuestionTwoActivity : AppCompatActivity() {

    private lateinit var mAdView : AdView
    private lateinit var binding: ActivityQuestionTwoBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Bind View
        binding = ActivityQuestionTwoBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialise ads
        MobileAds.initialize(this){}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)



        // Check for Radio Group Change
        binding.rgAnswers.setOnCheckedChangeListener { radioGroup, i ->
//            var selectedAnswer = binding.rgAnswers.checkedRadioButtonId
//            var userAnswer = findViewById<RadioButton>(selectedAnswer)
//
//            Log.i("hello", selectedAnswer.toString())
//            binding.rgAnswers.setBackgroundColor(resources.getColor(R.color.purple))
        }

        // Get answer and go to next question
        binding.acbBtnNext.setOnClickListener {
            // User answer
//            var selectedAnswer = binding.rgAnswers.checkedRadioButtonId
//            var userAnswer = findViewById<RadioButton>(selectedAnswer)
//
//            userAnswer.setBackgroundColor(Color.parseColor("#925FB2"))
        }
    }
}


