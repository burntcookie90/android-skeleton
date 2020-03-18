package com.example

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.databinding.ActivitySettingsBinding

class SettingsActivity : AppCompatActivity() {
  private var settingsFragment: SettingsFragment? = null

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    val binding = ActivitySettingsBinding.inflate(layoutInflater)
    setContentView(binding.root)

    if (settingsFragment == null) {
      settingsFragment = SettingsFragment()
      supportFragmentManager.beginTransaction()
          .replace(R.id.root, settingsFragment!!)
          .commitNowAllowingStateLoss()
    }
  }
}
