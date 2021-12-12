package com.example.accountbook

import android.app.Activity
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
import com.google.android.material.bottomsheet.BottomSheetDialogFragment


class PickTypeFragment : BottomSheetDialogFragment() {
    lateinit var binding: FragmentPickTypeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentPickTypeBinding.inflate(layoutInflater)

        binding.btnCash.setOnClickListener{
            val mActivity = activity as AddRecordActivity
            mActivity.receiveData(binding.btnCash.text.toString())
            dismiss()
        }

        return binding.root
    }
}