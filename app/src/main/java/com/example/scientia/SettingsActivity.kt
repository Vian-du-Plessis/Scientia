package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.scientia.databinding.ActivitySettingsBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class SettingsActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivitySettingsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)

        binding = ActivitySettingsBinding.inflate(layoutInflater);

        setContentView(binding.root);

        // Initialise Ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Navigates Back to Home Page
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent);
        }
    }
}