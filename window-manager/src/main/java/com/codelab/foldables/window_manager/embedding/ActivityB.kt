package com.codelab.foldables.window_manager.embedding

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codelab.foldables.window_manager.databinding.EmbeddedActivityBBinding

class ActivityB : AppCompatActivity() {
    private lateinit var binding: EmbeddedActivityBBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = EmbeddedActivityBBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}