package com.example.scientia

import android.annotation.SuppressLint
import android.app.ActionBar
import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.marginTop
import androidx.core.view.setMargins
import com.example.scientia.databinding.ActivityResultBinding
import com.example.scientia.models.Constants
import com.example.scientia.models.Constants.getChemQuestions
import com.example.scientia.models.Constants.getMathQuestions
import com.example.scientia.models.Questions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds

class ResultActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var binding: ActivityResultBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Bind View
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Initialise ads
        MobileAds.initialize(this){}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Shared Preferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Get Questions Count and Score
        var questionsCount = intent.getIntExtra("questionsCount", 0)
        var score = intent.getIntExtra("score", 0)

        // Set Results Text
        binding.tvResult.text = score.toString() + "/" + questionsCount.toString()

        // Category
        val category = intent.getStringExtra("category")
        binding.tvTitle.text = category

        // Get Questions of Selected Category
        var questions: ArrayList<Questions> = getChemQuestions()

        when (category) {
            "Math" -> {
                questions = getMathQuestions()
            }
            "Chemistry" -> {
                questions = getChemQuestions()
            }
            "Phys" -> {
                questions = Constants.getPhysQuestions()
            }
        }

        var highScore = intent.getBooleanExtra("highScore", false)

        if (highScore) {
            binding.tvScoreTitle.text = "High Score"

            // Create TextView
            val tv = TextView(this)
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            tv.textSize = 20f
            params.setMargins(0, convertPixels(40), 0, 0)
            tv.gravity = Gravity.CENTER
            tv.setTextColor(resources.getColor(R.color.white))
            tv.text = "Well done!!! You have beaten your High Score. Try playing some of our other categories to increase your knowledge on various subjects"

            binding.llQuestionsContainer.addView(tv)
        } else {
            // Loop Through all Questions and display them
            for(i in 0 until questions.count()){
                // Container of Questions and Answers
                val layout = LinearLayout(this)
                val params = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                // Styling
                layout.orientation = LinearLayout.VERTICAL
                layout.setPadding(0, 0, 0, 0)
                params.setMargins(0,0, 0, 100)
                layout.layoutParams = params

                // Questions TextView
                val textQ = TextView(this)
                textQ.text = "Q. " + questions[i].question

                textQ.textSize = 16f
                textQ.setTextColor(Color.parseColor("#F65A99"))
                textQ.gravity = Gravity.CENTER
                layout.addView(textQ)

                // Answers TextView
                val textA = TextView(this)
                textA.textSize = 16f
                textA.setTextColor(Color.parseColor("#FFFFFF"))
                textA.gravity = Gravity.CENTER

                // Add A. only to the first answer
                textA.text = "A. " + questions[i].answers[0]

                // Add Answer TextView to layout as child
                layout.addView(textA)

                // Add Layout with questions and answers to main Container
                binding.llQuestionsContainer.addView(layout)
            }
        }


        // Go back to Category Activity
        binding.ivBackbtn.setOnClickListener {
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
            finish()
        }

        // Play Category Again
        binding.acbBtnAgain.setOnClickListener {
            val intent = Intent(this, QuestionOneActivity::class.java)

            // Pass Category
            intent.putExtra("category", category)
            startActivity(intent)
            finish()
        }
    }

    // Convert Pixels to DP
    fun convertPixels(dp: Int): Int {
        val scale = resources.displayMetrics.density
        val dpasPixels = (dp * scale + 0.5f).toInt()

        return dpasPixels
    }
}