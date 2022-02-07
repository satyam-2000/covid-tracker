package com.internshala.covidtracker

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.animation.AnimationUtils
import android.widget.ImageView

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)




        supportActionBar?.hide()
        Handler().postDelayed({
            val intent= Intent(this@Splash,MainActivity::class.java)
            startActivity(intent)
            finish()
        },2500)
    }
}