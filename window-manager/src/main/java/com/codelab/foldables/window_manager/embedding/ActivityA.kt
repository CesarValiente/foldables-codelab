package com.codelab.foldables.window_manager.embedding

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.window.core.ExperimentalWindowApi
import androidx.window.embedding.SplitController
import com.codelab.foldables.window_manager.databinding.EmbeddedActivityABinding


class ActivityA : AppCompatActivity() {

    private lateinit var binding: EmbeddedActivityABinding

    @OptIn(ExperimentalWindowApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmbeddedActivityABinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.launchActivityB.setOnClickListener {
            val intent = Intent(this, ActivityB::class.java)
            startActivity(intent)
        }

        binding.isActivityEmbedded.setOnClickListener {
            val isActivityEmbedded = SplitController.getInstance().isActivityEmbedded(this)
            Toast.makeText(this, "activity embedded? $isActivityEmbedded", Toast.LENGTH_SHORT).show()
        }
    }

}