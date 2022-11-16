package com.codelab.foldables.window_manager.embedding

import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.window.embedding.SplitController
import com.codelab.foldables.window_manager.databinding.EmbeddedActivityDBinding

class ActivityD : AppCompatActivity() {
    private lateinit var binding: EmbeddedActivityDBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmbeddedActivityDBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.isActivityEmbedded.setOnClickListener {
            val isActivityEmbedded = SplitController.getInstance().isActivityEmbedded(this)
            Toast.makeText(this, "activity embedded? $isActivityEmbedded", Toast.LENGTH_SHORT)
                .show()
        }
    }
}