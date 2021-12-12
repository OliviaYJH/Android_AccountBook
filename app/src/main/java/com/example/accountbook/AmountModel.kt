package com.example.accountbook

import java.util.*

data class AmountModel(
    var id: Int = getAutoId(),
    var date:String = "",
    var type:String = "",
    var amount:Int,
    var context:String = ""
){
    companion object{
        fun getAutoId():Int{
            val random = Random()
            return random.nextInt(100)
        }
    }

}