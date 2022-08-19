package com.example.scientia

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Hide Status Bar
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN)

        setContentView(R.layout.activity_splash_screen)

        val backgroundImage : ImageView = findViewById(R.id.loader);
        val animation = AnimationUtils.loadAnimation( this,R.anim.loader );
        backgroundImage.startAnimation(animation);

        Handler().postDelayed({
            startActivity(Intent(this, HomeActivity::class.java));
            finish();
        }, 3000);


    }
}