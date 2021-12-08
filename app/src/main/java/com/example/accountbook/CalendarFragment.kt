package com.example.accountbook

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.accountbook.databinding.FragmentCalendarBinding
import com.example.accountbook.databinding.FragmentDayBinding

class CalendarFragment : Fragment() {

    private lateinit var binding: FragmentCalendarBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCalendarBinding.inflate(layoutInflater)

        return binding.root
    }


}