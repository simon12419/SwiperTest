package com.example.swipertest.ui.navWallet.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipertest.R
import kotlinx.android.synthetic.main.item_voucher.view.*

class VoucherAdapter : RecyclerView.Adapter<VoucherAdapter.ItemViewHolder>() {

    private var mDataList: List<String> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_voucher, viewGroup, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        val data = mDataList[position]
        holder.bind(data)
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(newDataList: List<String>?) {
        mDataList = newDataList ?: mutableListOf()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(data: String?) {
            itemView.tv_title.text = data
        }
    }
}