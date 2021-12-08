package com.example.accountbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accountbook.databinding.FragmentDayBinding

class DayFragment : Fragment() {


    private lateinit var binding: FragmentDayBinding
    val DB_NAME = "AccountBook.sql"
    val DB_VERSION = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDayBinding.inflate(layoutInflater)
        val helper = SqliteHelper(requireContext(), DB_NAME, DB_VERSION)

        return binding.root
    }



}