package com.example

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import com.example.databinding.ActivityMyBinding

class MyActivity : AppCompatActivity() {

  lateinit var binding: ActivityMyBinding

  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    binding = ActivityMyBinding.inflate(layoutInflater)
    setContentView(binding.root)

    binding.primaryButton.setOnClickListener {
      startActivity(Intent(this, SettingsActivity::class.java))
    }
  }
}
