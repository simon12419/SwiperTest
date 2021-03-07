package com.example.swipertest.data

class WalletEntity(
    val success: Boolean,
    val msg: String,
    val code: Int,
    val data: Data
)

class Data(
    val iconUrl: String,
    val rongMoney: Int,
    val XNC: Double,
    val expire: Int,
    val rewardList: List<Reward>,
    val voucherList: List<String>
)

class Reward(
    val name: String,
    val amount: Double
)