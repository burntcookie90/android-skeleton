package com.example

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_NO
import androidx.appcompat.app.AppCompatDelegate.MODE_NIGHT_YES
import androidx.preference.Preference
import androidx.preference.PreferenceFragmentCompat

class SettingsFragment : PreferenceFragmentCompat() {

  override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
    preferenceManager.sharedPreferencesName = "com.example_preferences"
    addPreferencesFromResource(R.xml.prefs)

    findPreference<Preference>(getString(R.string.pref_application_theme))!!.setOnPreferenceChangeListener { preference, newValue ->
      when (newValue) {
        "light" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_NO)
        "dark" -> AppCompatDelegate.setDefaultNightMode(MODE_NIGHT_YES)
      }

      true
    }
  }

}
