package com.example.scientia

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import com.google.android.material.textfield.TextInputLayout

class HomeActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)

        val til : TextInputLayout = findViewById(R.id.layout);
        val input : EditText = findViewById(R.id.name_input);
        val text = input.getText().length;
    }
}