package com.codelab.foldables.window_manager.embedding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.window.embedding.SplitController
import com.codelab.foldables.window_manager.databinding.EmbeddedActivityBBinding

class ActivityB : AppCompatActivity() {
    private lateinit var binding: EmbeddedActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmbeddedActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchActivityC.setOnClickListener {
            val intent = Intent(this, ActivityC::class.java)
            startActivity(intent)
        }
    }
}