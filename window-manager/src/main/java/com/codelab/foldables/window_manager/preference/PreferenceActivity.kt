package com.codelab.foldables.window_manager.preference

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.codelab.foldables.window_manager.R

class PreferenceActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_preference)

        supportFragmentManager
            .beginTransaction()
            .replace(R.id.preference_container, TwoPanePreference())
            .commit()
    }
}