package com.example.currencyconverter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.currencyconverter.R
import com.example.currencyconverter.adapters.CurrencyItem
import com.example.currencyconverter.adapters.CurrencySpinnerAdapter
import com.example.currencyconverter.interfaces.CurrencyServices
import com.example.currencyconverter.models.LatestRatesResponse
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val fromCurrencyItemList = populateFromCurrencyItem()
        val toCurrencyItemList=populateToCurrencyItem()


        val fromAdapter = CurrencySpinnerAdapter(this, fromCurrencyItemList)
        fromAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        from_spinner.adapter = fromAdapter

        val toAdapter = CurrencySpinnerAdapter(this, toCurrencyItemList)
        fromAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        to_spinner.adapter =toAdapter


//
//convertCurrencyFromAPI("GBP","USD",1)


    }


    private fun populateFromCurrencyItem():ArrayList<CurrencyItem>{
        val currencyItemList = ArrayList<CurrencyItem>()

        currencyItemList.add(CurrencyItem("EUR", R.drawable.european_flag))
        currencyItemList.add(CurrencyItem("USD", R.drawable.usa_flag))
        currencyItemList.add(CurrencyItem("GBP", R.drawable.uk_flag))
        currencyItemList.add(CurrencyItem("NGN", R.drawable.nigerian_flag))
        currencyItemList.add(CurrencyItem("NGN", R.drawable.poland_flag))

        return currencyItemList
    }



    private fun populateToCurrencyItem():ArrayList<CurrencyItem>{
        val currencyItemList = ArrayList<CurrencyItem>()
        currencyItemList.add(CurrencyItem("NGN", R.drawable.poland_flag))
        currencyItemList.add(CurrencyItem("EUR", R.drawable.european_flag))
        currencyItemList.add(CurrencyItem("USD", R.drawable.usa_flag))
        currencyItemList.add(CurrencyItem("GBP", R.drawable.uk_flag))
        currencyItemList.add(CurrencyItem("NGN", R.drawable.nigerian_flag))


        return currencyItemList
    }



    fun convertCurrencyFromAPI(from: String, to: String, amount: Int) {

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


            }

            override fun onFailure(call: Call<LatestRatesResponse>, t: Throwable) {
                Log.d("ERRORR", t.stackTraceToString())
            }


        })


    }

}