package com.example.myout

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat

class SetTimerActivityFragment : PreferenceFragmentCompat() {
    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)
    }
}