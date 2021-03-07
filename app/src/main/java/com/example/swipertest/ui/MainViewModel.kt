package com.example.swipertest.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.swipertest.data.WalletEntity
import com.example.swipertest.repository.DataRepository
import kotlinx.coroutines.launch

class MainViewModel(private val dataRepository: DataRepository) : ViewModel() {
    private val _walletResult = MutableLiveData<WalletEntity>()
    val walletResult: LiveData<WalletEntity> //使用者餘額
    get() = _walletResult

    fun queryData() {
        viewModelScope.launch {
            val result = dataRepository.getMockData()
            _walletResult.postValue(result)
        }
    }
}