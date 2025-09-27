package com.pluu.sample.logger

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.pluu.logger.CoreLogger
import com.pluu.logger.analytics
import com.pluu.logger.crashlytics
import com.pluu.logger.customEvent
import com.pluu.logger.firebase
import com.pluu.sample.logger.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(binding.root) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding.btnCrashlytics.setOnClickListener {
            CoreLogger.firebase
                .crashlytics
                .sendCrashlytics(IllegalStateException("Sample Error"))
        }

        binding.btnAnalytics.setOnClickListener {
            CoreLogger.firebase
                .analytics
                .sendEvent("Sample Key", mapOf("A" to 1))
        }

        binding.btnCustomEvent.setOnClickListener {
            CoreLogger.customEvent
                .event("Sample Event")
        }
    }
}