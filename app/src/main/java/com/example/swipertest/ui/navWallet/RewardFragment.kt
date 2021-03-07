package com.example.swipertest.ui.navWallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swipertest.R
import com.example.swipertest.data.Data
import com.example.swipertest.ui.navWallet.adapter.RewardAdapter
import kotlinx.android.synthetic.main.fragment_reward.*

class RewardFragment(private val data: Data) : Fragment() {

    private val mAdapter = RewardAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_reward, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupCardData()
        setupRecycleView()
        setupBtnMore()
    }

    private fun setupCardData() {
        tv_amount.text = data.rongMoney.toString()
        tv_xnc_amount.text = data.XNC.toString()
        pb_day.progress = data.expire
        tv_day.text = String.format(getString(R.string.desc_expire), data.expire)
    }

    private fun setupRecycleView() {
        rv_reward.adapter = mAdapter
        mAdapter.setData(data.rewardList)
    }

    private fun setupBtnMore() {
        btn_more.setOnClickListener {
            if (btn_more.text == getString(R.string.more)) {
                block_ad.visibility = View.GONE
                btn_more.setText(R.string.hide)
            } else {
                block_ad.visibility = View.VISIBLE
                btn_more.setText(R.string.more)
            }
        }
    }

}