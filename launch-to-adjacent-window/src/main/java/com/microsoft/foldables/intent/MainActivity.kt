package com.microsoft.foldables.intent

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        open_activity_in_adjacent_screen.setOnClickListener { openActivityInAdjacentWindow() }
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
