package com.example.currencyconverter.interfaces

import com.example.currencyconverter.Constants
import com.example.currencyconverter.data_models.LatestRatesResponse
import retrofit2.Call
import retrofit2.http.GET

interface CurrencyServices {

    @GET("latest?access_key=${Constants.API_KEY}")

    fun convertCurrency(
    ):Call<LatestRatesResponse>

}