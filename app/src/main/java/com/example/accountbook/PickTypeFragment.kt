package com.example.accountbook

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.accountbook.databinding.FragmentPickTypeBinding
import com.google.android.material.bottomsheet.BottomSheetDialog


class PickTypeFragment(context: Context) : BottomSheetDialog(context) {


    init{
        lateinit var binding: FragmentPickTypeBinding
        binding = FragmentPickTypeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnCash.setOnClickListener{
            var intent = Intent(getContext(), AddRecordActivity::class.java)
            intent.putExtra("type", binding.btnCash.text.toString())
            getContext().startActivity(intent)
            dismiss()
        }

    }

}