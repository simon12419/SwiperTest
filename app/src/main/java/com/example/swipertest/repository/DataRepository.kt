package com.example.swipertest.repository

import android.content.Context
import com.example.swipertest.data.WalletEntity
import com.example.swipertest.util.AssetsUtil
import com.google.gson.Gson


class DataRepository(private val androidContext: Context) {

    suspend fun getMockData(): WalletEntity {
        val json = AssetsUtil.readFileString(androidContext, "json_data.mock")
        return Gson().fromJson(json, WalletEntity::class.java)
    }

}