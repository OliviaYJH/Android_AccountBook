package com.example.accountbook

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.accountbook.databinding.FragmentDayBinding

class DayFragment : Fragment() {


    private lateinit var binding: FragmentDayBinding

    private lateinit var sqliteHelper: SQLiteHelper
    private lateinit var recyclerView: RecyclerView
    private var adapter: AmountAdapter? = null

    companion object{
        var atd: AmountModel? = null
    }



    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = FragmentDayBinding.inflate(layoutInflater)

        sqliteHelper = SQLiteHelper(requireContext())
        recyclerView = binding.recyclerView

        initRecyclerView()

        binding.recyclerView.addItemDecoration(DividerItemDecoration(context, LinearLayoutManager.VERTICAL))

        adapter?.setOnClickItem {
            var intent = Intent(activity, AddRecordActivity::class.java)
            intent.putExtra("id", it.id)
            intent.putExtra("date", it.date)
            intent.putExtra("type", it.type)
            intent.putExtra("amount", it.amount.toString())
            intent.putExtra("context", it.context)
            AddRecordActivity.check = false
            atd = it
            startActivity(intent)
        }

        adapter?.onClickDeleteItem{
            deleteAmount(it.id)
        }


        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val atdList = sqliteHelper.getAllAmount()
        adapter?.addItems(atdList)
    }

    fun deleteAmount(id:Int){
        val builder = AlertDialog.Builder(context)
        builder.setMessage("삭제하시겠습니까?")
        builder.setCancelable(true)

        builder.setPositiveButton("네"){ dialog, _ ->
            sqliteHelper.deleteAmountById(id)

            val atdList = sqliteHelper.getAllAmount()
            adapter?.addItems(atdList)

            dialog.dismiss()
        }
        builder.setNegativeButton("아니오"){ dialog, _ ->
            dialog.dismiss()
        }

        val alert = builder.create()
        alert.show()
    }

    fun initRecyclerView(){
        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = AmountAdapter()
        recyclerView.adapter = adapter
    }



}