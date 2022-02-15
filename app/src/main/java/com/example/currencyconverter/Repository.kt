package com.example.currencyconverter

import android.app.Application
import android.util.Log
import com.example.currencyconverter.interfaces.CurrencyServices
import com.example.currencyconverter.data_models.LatestRatesResponse
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class Repository(val application: Application) {


    init {
       getCurrencyDataFromAPI()
    }

    fun getCurrencyDataFromAPI() {

        var retrofit = Retrofit.Builder().baseUrl("http://data.fixer.io/api/")
            .addConverterFactory(GsonConverterFactory.create()).build()

        var service: CurrencyServices = retrofit.create(CurrencyServices::class.java)

        val call = service.convertCurrency()
        call.enqueue(object : Callback<LatestRatesResponse> {
            override fun onResponse(
                call: Call<LatestRatesResponse>,
                response: Response<LatestRatesResponse>
            ) {
                val latestResponse = response.body()



Constants.latestRatesResponse=latestResponse
            }

            override fun onFailure(call: Call<LatestRatesResponse>, t: Throwable) {
                Log.d("ERRORR", t.stackTraceToString())
            }


        })


    }
}