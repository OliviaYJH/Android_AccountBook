package com.example.accountbook

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class AmountAdapter: RecyclerView.Adapter<AmountAdapter.AmountViewHolder>() {
    private var atdList: ArrayList<AmountModel> = ArrayList()
    private var onClickItem:((AmountModel) -> Unit)? = null
    private var onClickDeleteItem:((AmountModel) -> Unit)? = null

    fun addItems(items: ArrayList<AmountModel>){
        this.atdList = items
        notifyDataSetChanged()
    }

    fun setOnClickItem(callback: (AmountModel)->Unit){
        this.onClickItem = callback
    }

    fun onClickDeleteItem(callback: (AmountModel)->Unit){
        this.onClickDeleteItem = callback
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = AmountViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_recycler, parent, false)
    )


    override fun onBindViewHolder(holder: AmountViewHolder, position: Int) {
        val atd = atdList[position]
        holder.bindView(atd)
        holder.itemView.setOnClickListener{
            onClickItem?.invoke(atd)
        }
        holder.btnDelete.setOnClickListener{
            onClickDeleteItem?.invoke(atd)
        }

    }

    override fun getItemCount(): Int {
        return atdList.size
    }


    class AmountViewHolder(var view: View): RecyclerView.ViewHolder(view){
        private var date = view.findViewById<TextView>(R.id.tv_recycler_datetime)
        private var type = view.findViewById<TextView>(R.id.tv_recycler_type)
        private var amount = view.findViewById<TextView>(R.id.tv_recycler_amount)
        private var context = view.findViewById<TextView>(R.id.tv_recycler_context)
        var btnDelete = view.findViewById<Button>(R.id.btn_recycler_delete)

        fun bindView(atd: AmountModel){
            date.text = atd.date
            type.text = atd.type
            amount.text = atd.amount.toString()
            context.text = atd.context
        }
    }

}