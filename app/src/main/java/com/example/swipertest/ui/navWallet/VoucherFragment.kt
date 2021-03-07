package com.example.swipertest.ui.navWallet

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.swipertest.R
import com.example.swipertest.ui.navWallet.adapter.VoucherAdapter
import kotlinx.android.synthetic.main.fragment_voucher.*

class VoucherFragment(private val voucherList: List<String>) : Fragment() {

    private val mAdapter = VoucherAdapter()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_voucher, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecycleView()
    }

    private fun setupRecycleView() {
        rv_voucher.adapter = mAdapter
        mAdapter.setData(voucherList)
    }

}