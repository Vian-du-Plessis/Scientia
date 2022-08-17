package com.example.scientia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.scientia.databinding.ActivitySettingsBinding
import com.example.scientia.models.Constants
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class SettingsActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivitySettingsBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Bind the View with This Kotlin File
        binding = ActivitySettingsBinding.inflate(layoutInflater);
        setContentView(binding.root);

        // Initialise Ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Shared Preferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Navigates Back to Home View
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java);
            startActivity(intent);
        }

        // Reset High Scores
        binding.btnReset.setOnClickListener {
            val toast = Toast.makeText(this, "Your High Score has been reset", Toast.LENGTH_SHORT)
            toast.show()

            editor.apply {
                putInt(Constants.MATH_SCORE, 0)
                putInt(Constants.CHEM_SCORE, 0)
                putInt(Constants.PHYS_SCORE, 0)
                putInt(Constants.MATH_COMPLETED, 0)
                putInt(Constants.CHEM_COMPLETED, 0)
                putInt(Constants.PHYS_COMPLETED, 0)
                apply()
            }
        }
    }
}