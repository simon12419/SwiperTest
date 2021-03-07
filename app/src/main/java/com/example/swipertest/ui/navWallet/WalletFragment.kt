package com.example.swipertest.ui.navWallet

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.swipertest.R
import com.example.swipertest.data.Data
import com.example.swipertest.ui.MainViewModel
import com.example.swipertest.ui.base.BaseFragment
import com.example.swipertest.ui.common.Vp2FragmentAdapter
import com.example.swipertest.util.DisplayUtil
import com.google.android.material.tabs.TabLayoutMediator
import kotlinx.android.synthetic.main.fragment_wallet.*

class WalletFragment : BaseFragment<MainViewModel>(MainViewModel::class) {
    companion object {
        private const val TAG = "WalletFragment"
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_wallet, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupStatusBar()
        queryData()
        initObserve()
    }

    private fun setupStatusBar() {
        block_status_bar.layoutParams.height = DisplayUtil.getStatusBarHeight(context)
    }

    private fun queryData() {
        viewModel.queryData()
    }

    private fun initObserve() {
        viewModel.walletResult.observe(viewLifecycleOwner, Observer {
            if (it.success) {
                setupHead(it.data.iconUrl)
                setViewPager(it.data)
            } else {
                Log.e(TAG, "query json data fail!!!")
            }

        })
    }

    private fun setupHead(imgUrl: String) {
        Glide.with(this)
            .load(imgUrl)
            .apply(RequestOptions().placeholder(R.drawable.circle))
            .into(iv_head) //載入頭像
    }

    private fun setViewPager(data: Data) {
        view_pager.adapter = Vp2FragmentAdapter(createFragList(data), this)

        TabLayoutMediator(tab_layout, view_pager) { tab, position ->
            when (position) {
                0 -> tab.setText(R.string.rb_record)
                1 -> tab.text = String.format(getString(R.string.exchange_voucher), data.voucherList.size)
                else -> {
                }
            }
        }.attach()
    }

    private fun createFragList(data: Data): List<Fragment> {
        return mutableListOf(RewardFragment(data), VoucherFragment(data.voucherList))
    }
}