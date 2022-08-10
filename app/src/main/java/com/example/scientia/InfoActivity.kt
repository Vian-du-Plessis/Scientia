package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.LinkMovementMethod
import android.view.Window
import android.view.WindowManager
import com.example.scientia.databinding.ActivityInfoBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class InfoActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivityInfoBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_info)

        binding = ActivityInfoBinding.inflate(layoutInflater);

        setContentView(binding.root);

        // Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Linking to github
        val linkText = binding.tvMyGitHub
        linkText.setMovementMethod(LinkMovementMethod.getInstance());

        // Navigates back to home page
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent);
        }
    }
}