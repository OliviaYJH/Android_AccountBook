package com.example.accountbook

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.accountbook.databinding.ActivityAddRecordBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AddRecordActivity : AppCompatActivity() {

    private lateinit var binding: ActivityAddRecordBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val now = System.currentTimeMillis() // 현재 시간 가져오기
        val simpleDateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.KOREA).format(now)
        binding.tvDate.setText(simpleDateFormat)

        binding.tvDate.setOnClickListener{
            val cal = Calendar.getInstance()
            DatePickerDialog(this, DatePickerDialog.OnDateSetListener{ datePicker, y, m, d ->
                // 선택한 날짜 보여주기
                binding.tvDate.setText("$y-${m+1}-$d")
            }, cal.get(Calendar.YEAR), cal.get(Calendar.MONTH), cal.get(Calendar.DATE)).show()
        }

        binding.etType.setOnClickListener{
            val confirmDialog = PickTypeFragment(this)
            confirmDialog.show()
            binding.etType.text = intent.getStringExtra("type")
        }


    }
}