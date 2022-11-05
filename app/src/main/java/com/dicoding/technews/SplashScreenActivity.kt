package com.dicoding.technews

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.delay
import java.util.concurrent.TimeUnit

class SplashScreenActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)

        startApp()
    }

    private fun startApp() = lifecycleScope.launchWhenCreated {
        delay(TimeUnit.SECONDS.toMillis(3))
        startActivity(Intent(this@SplashScreenActivity, MainActivity::class.java))
        finish()
    }
}