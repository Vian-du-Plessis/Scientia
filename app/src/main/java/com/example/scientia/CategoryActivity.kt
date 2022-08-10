package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.scientia.databinding.ActivityCategoryBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class CategoryActivity : AppCompatActivity() {

    private lateinit var mAdView : AdView;
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)
        // For API-30 and up
        //window.decorView.windowInsetsController!!.hide(
        //    android.view.WindowInsets.Type.statusBars()
        //)

        setContentView(R.layout.activity_category)

        binding = ActivityCategoryBinding.inflate(layoutInflater);

        setContentView(binding.root);

        // Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Navigates back to home page
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent);
        }
    }
}