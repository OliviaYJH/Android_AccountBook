package com.example.accountbook

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.accountbook.databinding.ActivityAddRecordBinding

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)


    }
}