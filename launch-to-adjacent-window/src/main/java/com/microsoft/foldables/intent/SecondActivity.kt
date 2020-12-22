package com.microsoft.foldables.intent

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.microsoft.foldables.intent.databinding.ActivitySecondBinding

class SecondActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(ActivitySecondBinding.inflate(layoutInflater).root)
    }
}
