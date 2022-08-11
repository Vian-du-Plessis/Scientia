package com.example.scientia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowManager
import com.example.scientia.databinding.ActivityQuestionOneBinding
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

        setContentView(R.layout.activity_question_one)

        binding = ActivityQuestionOneBinding.inflate(layoutInflater)

        setContentView(binding.root);

        //Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);
    }
}