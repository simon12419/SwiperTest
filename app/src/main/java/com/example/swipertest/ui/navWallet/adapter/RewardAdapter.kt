package com.example.swipertest.ui.navWallet.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.swipertest.R
import com.example.swipertest.data.Reward
import kotlinx.android.synthetic.main.item_reward.view.*
import kotlinx.android.synthetic.main.item_voucher.view.tv_title

class RewardAdapter : RecyclerView.Adapter<RewardAdapter.ItemViewHolder>() {

    private var mDataList: List<Reward> = mutableListOf()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ItemViewHolder {
        val layout = LayoutInflater.from(viewGroup.context).inflate(R.layout.item_reward, viewGroup, false)
        return ItemViewHolder(layout)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        try {
            val data = mDataList[position]
            holder.bind(data)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun getItemCount(): Int {
        return mDataList.size
    }

    fun setData(newDataList: List<Reward>?) {
        mDataList = newDataList ?: mutableListOf()
        notifyDataSetChanged()
    }


    inner class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @SuppressLint("SetTextI18n")
        fun bind(data: Reward?) {
            itemView.tv_title.text = data?.name
            itemView.tv_amount.text = "+"+data?.amount.toString()
        }
    }
}