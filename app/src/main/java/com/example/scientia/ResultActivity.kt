package com.example.scientia

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import com.example.scientia.databinding.ActivityResultBinding
import com.example.scientia.models.Constants.getMathQuestions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class ResultActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView;
    private lateinit var binding: ActivityResultBinding;

    @SuppressLint("ResourceAsColor")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        //Bind View
        binding = ActivityResultBinding.inflate(layoutInflater);
        setContentView(binding.root);

        //Initialise ads
        MobileAds.initialize(this){};
        mAdView = findViewById(R.id.adView);
        val adRequest = AdRequest.Builder().build();
        mAdView.loadAd(adRequest);

        //Go back to Category Activity
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java);
            startActivity(intent);
            finish();
        }

        //Get all Questions
        val questions = getMathQuestions();

        for(i in 0..questions.count() - 1){
            val layout = LinearLayout(this);
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            layout.setOrientation(LinearLayout.VERTICAL);
            layout.setPadding(0, 0, 0, 0);
            params.setMargins(0,0, 0, 100);
            layout.setLayoutParams(params);

            val textQ = TextView(this);
            textQ.text = "Q. " + questions[i].question;

            textQ.textSize = 16f
            textQ.setTextColor(Color.parseColor("#F65A99"));
            textQ.setGravity(Gravity.CENTER);
            layout.addView(textQ);

            for(d in 0..questions[i].answers.count() - 1){
                val textA = TextView(this);
                textA.textSize = 16f
                textA.setTextColor(Color.parseColor("#FFFFFF"));
                textA.setGravity(Gravity.CENTER);

                if(d == 0) {
                    textA.text = "A. " + questions[i].answers[d];
                } else {
                    textA.text = questions[i].answers[d];
                }
                layout.addView(textA);
            }

            binding.llQuestionsContainer.addView(layout);
        }
    }
}