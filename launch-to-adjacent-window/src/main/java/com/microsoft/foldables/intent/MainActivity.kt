package com.microsoft.foldables.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.foldables.intent.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.openActivityInAdjacentScreen.setOnClickListener { openActivityInAdjacentWindow() }
    }

    private fun openActivityInAdjacentWindow() {
        val intent = Intent(this, SecondActivity::class.java)
        intent.addFlags(
            Intent.FLAG_ACTIVITY_LAUNCH_ADJACENT or
                Intent.FLAG_ACTIVITY_NEW_TASK
        )
        startActivity(intent)
    }
}
