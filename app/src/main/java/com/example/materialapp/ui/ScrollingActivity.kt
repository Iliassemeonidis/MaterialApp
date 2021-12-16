package com.example.materialapp.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.materialapp.R
import com.example.materialapp.databinding.ActivityScrollingBinding
import com.google.android.material.snackbar.Snackbar

class ScrollingActivity : AppCompatActivity() {

    private lateinit var binding: ActivityScrollingBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScrollingBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
}