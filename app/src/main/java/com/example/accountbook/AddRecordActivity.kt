package com.example.accountbook

import android.app.DatePickerDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accountbook.databinding.ActivityAddRecordBinding
import com.example.accountbook.databinding.ActivityMainBinding
import com.example.accountbook.databinding.FragmentDayBinding
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import java.text.SimpleDateFormat
import java.time.LocalDateTime
import java.util.*

class AddRecordActivity : AppCompatActivity() {

    companion object{
        var check:Boolean = true
    }

    private lateinit var binding: ActivityAddRecordBinding
    private lateinit var Type: String

    private lateinit var sqliteHelper: SQLiteHelper
    private var adapter: AmountAdapter? = null
    private var atd: AmountModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityAddRecordBinding.inflate(layoutInflater)
        setContentView(binding.root)
        sqliteHelper = SQLiteHelper(this)

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


        // 선택한 값 보여주기
        if(!check){
            Toast.makeText(this, "false", Toast.LENGTH_SHORT).show()
            binding.tvDate.setText(intent.getStringExtra("date"))

        }
        binding.tvType.setText(intent.getStringExtra("type"))
        binding.etAsset.setText(intent.getStringExtra("amount"))
        binding.etContent.setText(intent.getStringExtra("context"))



        binding.tvType.setOnClickListener{
            // fragment 띄우기
            supportFragmentManager.beginTransaction()
                .replace(R.id.fragment, PickTypeFragment())
                .commit()
        }


        binding.btnSave.setOnClickListener{
            val date = binding.tvDate.text.toString()
            val amount = binding.etAsset.text.toString().toInt()
            val context = binding.etContent.text.toString()
            val type = binding.tvType.text.toString()

            if(check){
                if(context.isEmpty() || type.isEmpty()){
                    Toast.makeText(this, "모두 채워주세요", Toast.LENGTH_SHORT).show()
                }else{
                    val atd = AmountModel(date = date, amount = amount, context = context, type = type)
                    val status = sqliteHelper.insertAmount(atd)

                    if(status > -1){
                        Toast.makeText(this, "success", Toast.LENGTH_SHORT).show()
                        // 바로 보여주기
                        //val atdList = sqliteHelper.getAllAmount()
                        //adapter?.addItems(atdList)
                    }else{
                        Toast.makeText(this, "failed", Toast.LENGTH_SHORT).show()
                    }
                }
            }else{
                // update하기
                val atd = AmountModel(id = DayFragment.atd!!.id, date = date, amount = amount, context = context, type = type)

                val status = sqliteHelper.updateAmount(atd)
                if(status > -1){
                    val atdList = sqliteHelper.getAllAmount()
                    adapter?.addItems(atdList)
                }else{
                    Toast.makeText(this, "update failed", Toast.LENGTH_SHORT).show()
                }
                check = true
            }


            finish()
        }
    }

    fun receiveData(type: String){
        Type = type
        binding.tvType.text = type
    }






}