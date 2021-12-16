package com.codelab.foldables.window_manager.preference

import androidx.preference.PreferenceFragmentCompat
import androidx.preference.PreferenceHeaderFragmentCompat

class TwoPanePreference : PreferenceHeaderFragmentCompat() {

    override fun onCreatePreferenceHeader(): PreferenceFragmentCompat {
        return MainPreferenceFragment()
    }
}