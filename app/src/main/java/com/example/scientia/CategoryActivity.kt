package com.example.scientia

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.Window
import android.view.WindowManager
import com.example.scientia.databinding.ActivityCategoryBinding
import com.example.scientia.models.Constants
import com.example.scientia.models.Constants.getChemQuestions
import com.example.scientia.models.Constants.getMathQuestions
import com.example.scientia.models.Constants.getPhysQuestions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import java.lang.reflect.Array.get

class CategoryActivity : AppCompatActivity() {

    private lateinit var mAdView : AdView
    private lateinit var binding: ActivityCategoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Bind View
        binding = ActivityCategoryBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialise ads
        MobileAds.initialize(this){}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Shared Preferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Get Category Scores
        val mathScore = sharedPref.getInt(Constants.MATH_SCORE, 0)
        val chemScore = sharedPref.getInt(Constants.CHEM_SCORE, 0)
        val physScore = sharedPref.getInt(Constants.PHYS_SCORE, 0)

        // Get Amount of Completed Questions
        val mathCompleted = sharedPref.getInt(Constants.MATH_COMPLETED, 0)
        val chemCompleted = sharedPref.getInt(Constants.CHEM_COMPLETED, 0)
        val physCompleted = sharedPref.getInt(Constants.PHYS_COMPLETED, 0)

        Log.i("Complete", chemCompleted.toString())

        // Number of Questions in Each Category
        val mathTotal = getMathQuestions().count()
        val chemTotal = getChemQuestions().count()
        val physTotal = getPhysQuestions().count()

        // Completed percentage
        val math = (mathCompleted * 100) / mathTotal
        val chem = (chemCompleted * 100) / chemTotal
        val phys = (physCompleted * 100) / physTotal

        // High Score of Categories
        binding.tvMathHs.text = mathScore.toString()
        binding.tvChemHs.text = chemScore.toString()
        binding.tvPhysHs.text = physScore.toString()

        // Completion of Categories
        binding.tvMathCompletion.text = "$math%"
        binding.tvChemCompletion.text = "$chem%"
        binding.tvPhysCompletion.text = "$phys%"

        // Navigates back to home page
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, HomeActivity::class.java)
            startActivity(intent)
        }

        // Select Math Category
        binding.cvMath.setOnClickListener {
            // Navigate to Questions Activity
            val intent = Intent(this, QuestionOneActivity::class.java)

            // Pass the category to questionActivty
            intent.putExtra("category", "Math")

            // Start Activity
            startActivity(intent)
        }

        // Select Chemistry Category
        binding.cvChem.setOnClickListener {
            // Navigate to Questions Activity
            val intent = Intent(this, QuestionOneActivity::class.java)

            // Pass the category to questionActivty
            intent.putExtra("category", "Chemistry")

            // Start Activity
            startActivity(intent)
        }

        // Select Physics Category
        binding.cvPhys.setOnClickListener {
            // Navigate to Questions Activity
            val intent = Intent(this, QuestionOneActivity::class.java)

            // Pass the category to questionActivty
            intent.putExtra("category", "Physics")

            // Start Activity
            startActivity(intent)
        }
    }
}