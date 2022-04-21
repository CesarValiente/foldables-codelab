package com.codelab.foldables.window_manager.embedding

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

        binding.isActivityEmbedded.setOnClickListener {
            val isActivityEmbedded = SplitController.getInstance().isActivityEmbedded(this)
            Toast.makeText(this, "activity embedded? $isActivityEmbedded", Toast.LENGTH_SHORT).show()
        }
    }
}