package com.example.scientia

import android.content.Intent
import android.content.res.ColorStateList
import android.graphics.Color
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
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

        // Initialise ads
        MobileAds.initialize(this){}
        mAdView = findViewById(R.id.adView)
        val adRequest = AdRequest.Builder().build()
        mAdView.loadAd(adRequest)

        // Question Number
        val questionNumber = intent.getIntExtra("questionNumber", 0)

        // Score
        val score = intent.getIntExtra("score", 0)

        // Category
        val category = intent.getStringExtra("category")

        // Set View Title
        binding.tvTitle.text = category.toString()

        // Get Questions of Selected Category
        var questions: ArrayList<Questions> = getChemQuestions()

        when (category) {
            "Math" -> {
                questions = getMathQuestions()
            }
            "Chem" -> {
                questions = getChemQuestions()
            }
            "Physics" -> {
                questions = getPhysQuestions()
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

        // Check Question Answer Method
        if (currentQuestion.type == "Multiple") {
            // Render Question type
            val card = CardView(this)
            val params = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            card.radius = 20f
            params.setMargins(0, 50, 0, 0)
            card.layoutParams = params

            // Radio Group
            val rgParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            rg.setPadding(20, 20, 20, 20)
            rg.layoutParams = rgParams

            // Add Card and RadioGroup to View
            card.addView(rg)
            binding.llAnswersContainer.addView(card)

            for(i in 0 until currentQuestion.options!!.count()) {
                // RadioButton
                val radiobtn = AppCompatRadioButton(this)
                val rbParams = ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.WRAP_CONTENT
                )
                radiobtn.text = currentQuestion.options!![i]
                radiobtn.setTextColor(resources.getColor(R.color.black))
                radiobtn.textSize = 21f
                radiobtn.layoutParams = rgParams

                // Set Tint Color to Radio Button
                val textColor = resources.getColor(R.color.pink)
                radiobtn.setButtonTintList(ColorStateList.valueOf(textColor));

                // Ad RadioButtons to RadioGroup
                rg.addView(radiobtn)
            }
        } else if (currentQuestion.type == "Input") {
            // Create TextField TextInputLayout
            val tfTil = TextInputLayout(this)
            val tfTilParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            tfTil.hintTextColor = ColorStateList.valueOf(resources.getColor(R.color.white))
            tfTil.boxStrokeWidth = 0
            tfTil.boxStrokeWidthFocused = 0
            tfTil.counterTextColor = ColorStateList.valueOf(resources.getColor(R.color.white))
            tfTil.isHintEnabled = false
            tfTilParams.setMargins(0, 20, 0, 0)

            // Create EditText
            val et = EditText(this)
            val etParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            et.background = resources.getDrawable(R.drawable.custom_input)
            et.hint = "Enter your answer"
            et.setPadding(15, 15, 15 ,15)
            et.setTextColor(resources.getColor(R.color.white))
            et.setHintTextColor(resources.getColor(R.color.gray))
            et.layoutParams = etParams

            binding.llAnswersContainer.addView(et)

            // Create AppCompatButton
            val apBtn = AppCompatButton(this)
            val apBtnParams = ActionBar.LayoutParams(
                ActionBar.LayoutParams.MATCH_PARENT,
                ActionBar.LayoutParams.WRAP_CONTENT
            )
            apBtnParams.setMargins(40, 50, 40, 0)
            apBtn.setPadding(10)
            apBtn.background = resources.getDrawable(R.drawable.my_button)
            apBtn.backgroundTintList = ColorStateList.valueOf(resources.getColor(R.color.pink))
            apBtn.isAllCaps = false
            apBtn.text = "Next"
            apBtn.setTextColor(resources.getColor(R.color.white))
        }


        // Navigate to Category View
        binding.ivBackbtn.setOnClickListener {
            // Start Activity
            val intent = Intent(this, CategoryActivity::class.java)
            startActivity(intent)
        }

        // Go to Next Question or Results View
        binding.acbBtnNext.setOnClickListener {
            // User Answer
            var answer: String = ""

            if (currentQuestion.type == "Multiple") {
                var selectedAnswer = rg.checkedRadioButtonId

                if (selectedAnswer == -1) {
                    val toast = Toast.makeText(this, "Please select your answer", LENGTH_SHORT)
                    toast.show()
                } else {
                    answer = findViewById<AppCompatRadioButton>(selectedAnswer).text.toString()
                }
            } else {
                answer = binding.tfTilAnswer.editText?.text.toString().trim()
            }
            Log.i("test", answer)

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
}