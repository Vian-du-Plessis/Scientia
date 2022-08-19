package com.example.scientia

import android.content.Context
import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.widget.EditText
import android.widget.RadioGroup
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRadioButton
import androidx.cardview.widget.CardView
import androidx.core.view.setPadding
import com.example.scientia.databinding.ActivityQuestionOneBinding
import com.example.scientia.models.Constants
import com.example.scientia.models.Constants.getChemQuestions
import com.example.scientia.models.Constants.getMathQuestions
import com.example.scientia.models.Constants.getPhysQuestions
import com.example.scientia.models.Questions
import com.google.android.gms.ads.AdRequest
import com.google.android.gms.ads.AdView
import com.google.android.gms.ads.MobileAds
import com.google.android.material.textfield.TextInputLayout

class QuestionOneActivity : AppCompatActivity() {

    private lateinit var mAdView: AdView
    private lateinit var binding: ActivityQuestionOneBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        // Bind View
        binding = ActivityQuestionOneBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        // Initialise ads
//        MobileAds.initialize(this){}
//        mAdView = findViewById(R.id.adView)
//        val adRequest = AdRequest.Builder().build()
//        mAdView.loadAd(adRequest)

        // Shared Preferences
        val sharedPref = getSharedPreferences("myPref", Context.MODE_PRIVATE)
        val editor = sharedPref.edit()

        // Question Number
        val questionNumber = intent.getIntExtra("questionNumber", 0)

        // Get Category Scores
        val mathScore = sharedPref.getInt(Constants.MATH_SCORE, 0)
        val chemScore = sharedPref.getInt(Constants.CHEM_SCORE, 0)
        val physScore = sharedPref.getInt(Constants.PHYS_SCORE, 0)

        // Get Completion Rate
        val mathComplete = sharedPref.getInt(Constants.MATH_COMPLETED, 0)
        val chemComplete = sharedPref.getInt(Constants.CHEM_COMPLETED, 0)
        val physComplete = sharedPref.getInt(Constants.PHYS_COMPLETED, 0)

        // Score
        val score = intent.getIntExtra("score", 0)

        // Category
        val category = intent.getStringExtra("category")

        // Set View Title
        binding.tvTitle.text = category.toString()

        // Get Questions of Selected Category
        var questions: ArrayList<Questions> = getChemQuestions()

        // Get Questions from Categories
        when (category) {
            "Math" -> {
                questions = getMathQuestions()
                if (score > mathScore) {
                    editor.apply {
                        putInt(Constants.MATH_SCORE, score)
                        apply()
                    }
                }
                if (questionNumber > mathComplete) {
                    editor.apply {
                        putInt(Constants.MATH_COMPLETED, questionNumber)
                        apply()
                    }
                }
            }
            "Chemistry" -> {
                questions = getChemQuestions()
                if (score > chemScore) {
                    editor.apply {
                        putInt(Constants.CHEM_SCORE, score)
                        apply()
                    }
                }
                if (questionNumber > chemComplete) {
                    editor.apply {
                        putInt(Constants.CHEM_COMPLETED, questionNumber)
                        apply()
                    }
                }
            }
            "Physics" -> {
                questions = getPhysQuestions()
                if (score > physScore) {
                    editor.apply {
                        putInt(Constants.PHYS_SCORE, score)
                        apply()
                    }
                }
                if (questionNumber > physComplete) {
                    editor.apply {
                        putInt(Constants.PHYS_COMPLETED, questionNumber)
                        apply()
                    }
                }
            }
        }

        // Select Current Question
        val currentQuestion = questions[questionNumber]

        // TextView set Question Text
        binding.tvQuestionText.text = currentQuestion.question

        // TextView set Score Text
        binding.tvScore.text = score.toString()

        // Set Progress
        val progressNumber = questionNumber + 1
        binding.tvQuestionNumber.text = "${progressNumber}/${questions.count()}"

        // Progress Bar
        val progressBar = binding.pbLine
        progressBar.max = questions.count()
        progressBar.progress = progressNumber

        // Radio Group
        val rg = RadioGroup(this)

        // Create AppCompatButton
        val apBtn = AppCompatButton(this)
        val apBtnParams = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.WRAP_CONTENT
        )
        apBtnParams.setMargins(convertPixels(40), convertPixels(50), convertPixels(40), 0)
        apBtn.setPadding(10, 10, 10, 10)
        apBtn.background = resources.getDrawable(R.drawable.my_button)
        apBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.pink))
        apBtn.isAllCaps = false
        apBtn.text = "Next"
        apBtn.setTextColor(resources.getColor(R.color.white))
        apBtn.layoutParams = apBtnParams

        // TextField
        val tfTil = TextInputLayout(this)

        // Edit Text
        val et = EditText(this)

        // Check Question Answer Method
        if (currentQuestion.type == "Multiple") {
            // Render Question type
            val card = CardView(this)
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            card.radius = 20f
            card.layoutParams = params

            // Radio Group
            val rgParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            rg.setPadding(convertPixels(20))
            rg.layoutParams = rgParams

            // Add Card and RadioGroup to View
//            card.addView(rg)
            binding.llAnswersContainer.addView(rg)

            // Multiple Choice Options
            val options = currentQuestion.options!!.shuffled()

            for(i in 0 until options.count()) {
                // RadioButton
                val radiobtn = AppCompatRadioButton(this)
                val rbParams = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                radiobtn.text = options[i]
                radiobtn.setTextColor(resources.getColor(R.color.white))
                radiobtn.textSize = 21f
                radiobtn.layoutParams = rbParams
                rbParams.setMargins(0, convertPixels(5), 0, convertPixels(5))

                // Set Tint Color to Radio Button
                val textColor = resources.getColor(R.color.pink)
                radiobtn.buttonTintList = ColorStateList.valueOf(textColor);

                // Ad RadioButtons to RadioGroup
                rg.addView(radiobtn)
            }

//            binding.llAnswersContainer.addView(apBtn)
        } else if (currentQuestion.type == "Input") {
            // Create TextField TextInputLayout
            val tfTilParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            tfTil.hintTextColor = ColorStateList.valueOf(resources.getColor(R.color.white))
            tfTil.boxStrokeWidth = 0
            tfTil.boxStrokeWidthFocused = 0
            tfTil.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.white))
            tfTil.isHintEnabled = false
            tfTilParams.setMargins(0, convertPixels(20), 0, 0)

            // Create EditText
            val etParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            et.background = resources.getDrawable(R.drawable.custom_input)
            et.hint = "Enter your answer"
            et.setPadding(convertPixels(15))
            et.setTextColor(resources.getColor(R.color.white))
            et.setHintTextColor(resources.getColor(R.color.gray))
            et.isSingleLine = true
            et.layoutParams = etParams

            binding.llAnswersContainer.addView(et)

            binding.llAnswersContainer.addView(apBtn)
        }

        // Navigate to Category View
        binding.ivBackbtn.setOnClickListener {
            // Start Activity
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

        // Go to next questions when user selects a answer
        rg.setOnCheckedChangeListener { radioGroup, i ->
            // User Answer
            var selectedAnswer = rg.checkedRadioButtonId
            var answer = findViewById<AppCompatRadioButton>(selectedAnswer).text.toString()

            // If this is the last question
            if(questionNumber === questions.count() - 1){
                // Intent for Next Activity
                val intent = Intent(this, ResultActivity::class.java)

                // Check if answer is correct
                if (currentQuestion.answers.toString().lowercase().contains(answer.lowercase())) {
                    // Pass Score and QuestionsCount
                    intent.putExtra("score", score + 1)
                    intent.putExtra("questionsCount", questions.count())
                } else {
                    // Pass Score and QuestionsCount
                    intent.putExtra("score", score)
                    intent.putExtra("questionsCount", questions.count())
                }

                when (category) {
                    "Math" -> {
                        if (score > mathScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.MATH_COMPLETED, questionNumber + 1)
                            putInt(Constants.MATH_SCORE, score + 1)
                            apply()
                        }
                    }
                    "Chemistry" -> {
                        if (score > chemScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.CHEM_COMPLETED, questionNumber + 1)
                            putInt(Constants.CHEM_SCORE, score + 1)
                            apply()
                        }
                    }
                    "Phys" -> {
                        if (score > physScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.PHYS_COMPLETED, questionNumber + 1)
                            putInt(Constants.PHYS_SCORE, score + 1)
                            apply()
                        }
                    }
                }

                // Pass Category
                intent.putExtra("category", category)

                // Start Activity
                startActivity(intent)
                finish()
            } else {
                // If answer is Empty
                if (TextUtils.isEmpty(answer)) {
                    // Show toast with a message
                    val toast = Toast.makeText(this, "Please enter a answer" , Toast.LENGTH_LONG)
                    toast.show()
                } else {
                    // Check if answer is correct
                    if (currentQuestion.answers.toString().lowercase().contains(answer.lowercase())) {
                        intent.putExtra("score", score + 1)
                        intent.putExtra("questionsCount", questions.count())
                    }

                    // Increment Question Number, and pass the value to next activity
                    intent.putExtra("questionNumber", questionNumber + 1)

                    // Removes Animation when Starting Activity
                    finish()
                    overridePendingTransition(0, 0)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }

        et.setOnKeyListener(View.OnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == KeyEvent.ACTION_UP) {
                //Perform Code
                var answer = et.text.toString().trim()

                // If this is the last question
                if(questionNumber === questions.count() - 1){
                    // Intent for Next Activity
                    val intent = Intent(this, ResultActivity::class.java)

                    // Check if answer is correct
                    if (currentQuestion.answers.toString().lowercase().contains(answer.lowercase())) {
                        // Pass Score and QuestionsCount
                        intent.putExtra("score", score + 1)
                        intent.putExtra("questionsCount", questions.count())
                    } else {
                        // Pass Score and QuestionsCount
                        intent.putExtra("score", score)
                        intent.putExtra("questionsCount", questions.count())
                    }

                    when (category) {
                        "Math" -> {
                            if (score > mathScore) {
                                intent.putExtra("highScore", true)
                            }
                            editor.apply {
                                putInt(Constants.MATH_COMPLETED, questionNumber + 1)
                                putInt(Constants.MATH_SCORE, score + 1)
                                apply()
                            }
                        }
                        "Chemistry" -> {
                            if (score > chemScore) {
                                intent.putExtra("highScore", true)
                            }
                            editor.apply {
                                putInt(Constants.CHEM_COMPLETED, questionNumber + 1)
                                putInt(Constants.CHEM_SCORE, score + 1)
                                apply()
                            }
                        }
                        "Phys" -> {
                            if (score > physScore) {
                                intent.putExtra("highScore", true)
                            }
                            editor.apply {
                                putInt(Constants.PHYS_COMPLETED, questionNumber + 1)
                                putInt(Constants.PHYS_SCORE, score + 1)
                                apply()
                            }
                        }
                    }

                    // Pass Category
                    intent.putExtra("category", category)

                    // Start Activity
                    startActivity(intent)
                    finish()
                } else {
                    // If answer is Empty
                    if (TextUtils.isEmpty(answer)) {
                        // Show toast with a message
                        val toast = Toast.makeText(this, "Please enter a answer", Toast.LENGTH_LONG)
                        toast.show()
                    } else {
                        // Check if answer is correct
                        if (currentQuestion.answers.toString().lowercase()
                                .contains(answer.lowercase())
                        ) {
                            intent.putExtra("score", score + 1)
                            intent.putExtra("questionsCount", questions.count())
                        }

                        // Increment Question Number, and pass the value to next activity
                        intent.putExtra("questionNumber", questionNumber + 1)

                        // Removes Animation when Starting Activity
                        finish()
                        overridePendingTransition(0, 0)
                        startActivity(intent)
                        overridePendingTransition(0, 0)
                    }
                }
                return@OnKeyListener true
            }
            false
        })

        // Go to Next Question or Results View
        apBtn.setOnClickListener {
            // User Answer
            var answer = et.text.toString().trim()

            // If this is the last question
            if(questionNumber === questions.count() - 1){
                // Intent for Next Activity
                val intent = Intent(this, ResultActivity::class.java)

                // Check if answer is correct
                if (currentQuestion.answers.toString().lowercase().contains(answer.lowercase())) {
                    // Pass Score and QuestionsCount
                    intent.putExtra("score", score + 1)
                    intent.putExtra("questionsCount", questions.count())
                } else {
                    // Pass Score and QuestionsCount
                    intent.putExtra("score", score)
                    intent.putExtra("questionsCount", questions.count())
                }

                when (category) {
                    "Math" -> {
                        if (score > mathScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.MATH_COMPLETED, questionNumber + 1)
                            putInt(Constants.MATH_SCORE, score + 1)
                            apply()
                        }
                    }
                    "Chemistry" -> {
                        if (score > chemScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.CHEM_COMPLETED, questionNumber + 1)
                            putInt(Constants.CHEM_SCORE, score + 1)
                            apply()
                        }
                    }
                    "Phys" -> {
                        if (score > physScore) {
                            intent.putExtra("highScore", true)
                        }
                        editor.apply {
                            putInt(Constants.PHYS_COMPLETED, questionNumber + 1)
                            putInt(Constants.PHYS_SCORE, score + 1)
                            apply()
                        }
                    }
                }

                // Pass Category
                intent.putExtra("category", category)

                // Start Activity
                startActivity(intent)
                finish()
            } else {
                // If answer is Empty
                if (TextUtils.isEmpty(answer)) {
                    // Show toast with a message
                    val toast = Toast.makeText(this, "Please enter a answer" , Toast.LENGTH_LONG)
                    toast.show()
                } else {
                    // Check if answer is correct
                    if (currentQuestion.answers.toString().lowercase().contains(answer.lowercase())) {
                        intent.putExtra("score", score + 1)
                        intent.putExtra("questionsCount", questions.count())
                    }

                    // Increment Question Number, and pass the value to next activity
                    intent.putExtra("questionNumber", questionNumber + 1)

                    // Removes Animation when Starting Activity
                    finish()
                    overridePendingTransition(0, 0)
                    startActivity(intent)
                    overridePendingTransition(0, 0)
                }
            }
        }
    }


    // Convert Pixels to DP
    fun convertPixels(dp: Int): Int {
        val scale = resources.displayMetrics.density
        val dpasPixels = (dp * scale + 0.5f).toInt()

        return dpasPixels
    }
}