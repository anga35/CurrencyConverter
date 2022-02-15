package com.example.currencyconverter.view_models

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import com.example.currencyconverter.Repository
import com.example.currencyconverter.data_models.LatestRatesResponse

class CurrencyViewModel(application: Application):AndroidViewModel(application) {

lateinit var repository: Repository
lateinit var currencyConvertLiveData:MutableLiveData<String>

init {
    currencyConvertLiveData=MutableLiveData<String>()
repository= Repository(application)


}

}