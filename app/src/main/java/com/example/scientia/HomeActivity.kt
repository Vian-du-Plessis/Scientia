package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Window
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.WindowManager
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.example.scientia.databinding.ActivityHomeBinding
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivityHomeBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_home)

        binding = ActivityHomeBinding.inflate(layoutInflater);

        setContentView(binding.root);

        // Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        // Check if username has already been entered
        val userfield = binding.etNameInput;
            //TODO: Check if username has already been entered
//        if ( "LocalStorage"  == "undefined" ) {
//            //If name exists auto fill field
//            userfield.setText("Vian");
//        }

        // Run when start button is pressed
        binding.acbBtnStart.setOnClickListener {
            val username = binding.etNameInput.text;
            val inputLayout = binding.tfTextInputLayout;
            if ( username.length > 16 ) {
                inputLayout.setError("This name is too long.");
            } else if ( username.length < 1 ) {
                inputLayout.setError("Please enter your name.")
            } else {
                val intent = Intent(this, QuestionOneActivity::class.java);
                startActivity(intent);
            }
        }

        // Navigates to Info Page
        binding.ivInfobtn.setOnClickListener {
            val intent = Intent(this, InfoActivity::class.java);
            startActivity(intent);
        }

        // Navigates to Settings Page
        binding.ivSettingbtn.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java);
            startActivity(intent);
        }
    }
}