package com.codelab.foldables.window_manager.preference

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.codelab.foldables.window_manager.R

class MainPreferenceFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.preferences, rootKey)
    }
}