package com.example.accountbook

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentPagerAdapter
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.example.accountbook.databinding.ActivityMainBinding
import com.example.accountbook.databinding.FragmentDayBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        // 페이지 fragment 데이터 목록
        val list  = listOf(DayFragment(), CalendarFragment(), WeekFragment(), MonthFragment(), AccountsFragment())

        // adapter 생성
        val pagerAdapter = FragmentPagerAdapter(list, this)

        // adapter와 viewpager 연결
        binding.viewpager2.adapter = pagerAdapter

        // tab item 개수만큼 제목을 목록으로 생성
        val titles = listOf("일일", "달력", "주별", "월별", "결산")

        // tablayout과 viewpager 연결
        TabLayoutMediator(binding.tablayout, binding.viewpager2){ tab, position ->
            tab.text = titles.get(position)
        }.attach()

        binding.ibAdd.setOnClickListener {
           startActivity(Intent(this, AddRecordActivity::class.java))
        }



    }


}

// TabLayout
class FragmentPagerAdapter(val fragmentList:List<Fragment>, fragmentActivity: FragmentActivity)
                                                        : FragmentStateAdapter(fragmentActivity){
    override fun getItemCount(): Int {
        return fragmentList.size
    }

    override fun createFragment(position: Int): Fragment {
    return fragmentList.get(position)
    }

}