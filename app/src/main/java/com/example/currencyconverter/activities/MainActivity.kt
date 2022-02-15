package com.example.currencyconverter.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import com.example.currencyconverter.Constants
import com.example.currencyconverter.R
import com.example.currencyconverter.adapters.CurrencyItem
import com.example.currencyconverter.adapters.CurrencySpinnerAdapter
import com.example.currencyconverter.view_models.CurrencyViewModel
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(),AdapterView.OnItemSelectedListener {
    var currencyViewModel:CurrencyViewModel?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        currencyViewModel = ViewModelProvider(this).get(CurrencyViewModel::class.java)
        currencyViewModel!!.currencyConvertLiveData!!.observe(this, {

            et_toCurrency.setText(it)


        })


        val fromCurrencyItemList = populateFromCurrencyItem()
        val toCurrencyItemList = populateToCurrencyItem()


        val fromAdapter = CurrencySpinnerAdapter(this, fromCurrencyItemList)
        fromAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        from_spinner.adapter = fromAdapter

        val toAdapter = CurrencySpinnerAdapter(this, toCurrencyItemList)
        fromAdapter.setDropDownViewResource(R.layout.custom_spinner_dropdown_item)
        to_spinner.adapter = toAdapter


to_spinner.onItemSelectedListener=this

        btn_convert.setOnClickListener {

            try {
                val convertFrom = et_fromCurrency.text.toString()
//                val currentCurrency = (from_spinner.selectedItem as CurrencyItem)
                val toCurrency = (to_spinner.selectedItem as CurrencyItem)
                var selectedCurrencyValue: Float? = null
                val currentRate = Constants!!.latestRatesResponse!!.rates
                if (!convertFrom.isNullOrEmpty()) {

                    when (toCurrency.currencyName) {
                        "EUR" -> {

                            selectedCurrencyValue = currentRate.EUR.toFloat()
                        }
                        "NGN" -> {
                            selectedCurrencyValue = currentRate.NGN.toFloat()

                        }
                        "GBP" -> {
                            selectedCurrencyValue = currentRate.GBP.toFloat()
                        }
                        "USD" -> {
                            selectedCurrencyValue = currentRate.USD.toFloat()
                        }
                        "PLN" -> {
                            selectedCurrencyValue = currentRate.PLN.toFloat()
                        }

                        else -> {
                            selectedCurrencyValue = null
                        }
                    }

                    if (selectedCurrencyValue != null) {

                        tv_to_currencyHint.text=toCurrency.currencyName

                        val convertedValue = convertFrom.toFloat() * selectedCurrencyValue


                        currencyViewModel!!.currencyConvertLiveData.value = convertedValue.toString()

                    }


                }


            }catch (e:Exception){
                Log.d("CONVERSION_ERROR",e.stackTraceToString())

showErrorSnackBar("Wrong Input,Try Again")


            }

        }
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, p3: Long) {


        try {

            val convertFrom = et_fromCurrency.text.toString()
            val currentCurrency =parent!!.selectedItem as CurrencyItem
            var selectedCurrencyValue: Float? = null
            val currentRate = Constants!!.latestRatesResponse!!.rates
            if (!convertFrom.isNullOrEmpty()) {

                when (currentCurrency.currencyName) {
                    "EUR" -> {
                        selectedCurrencyValue = currentRate.EUR.toFloat()
                    }
                    "NGN" -> {
                        selectedCurrencyValue = currentRate.NGN.toFloat()

                    }
                    "GBP" -> {
                        selectedCurrencyValue = currentRate.GBP.toFloat()
                    }
                    "USD" -> {
                        selectedCurrencyValue = currentRate.USD.toFloat()
                    }
                    "PLN" -> {
                        selectedCurrencyValue = currentRate.PLN.toFloat()
                    }

                    else -> {
                        selectedCurrencyValue = null
                    }
                }

                if (selectedCurrencyValue != null) {

                    tv_to_currencyHint.text=currentCurrency.currencyName
                    val convertedValue = convertFrom.toFloat() * selectedCurrencyValue


                    currencyViewModel!!.currencyConvertLiveData.value = convertedValue.toString()

                }


            }


        }catch (e:Exception){
            Log.d("CONVERSION_ERROR",e.stackTraceToString())

            showErrorSnackBar("Wrong Input,Try Again")


        }

    }

    override fun onNothingSelected(p0: AdapterView<*>?) {
        TODO("Not yet implemented")
    }


    private fun populateFromCurrencyItem(): ArrayList<CurrencyItem> {
        val currencyItemList = ArrayList<CurrencyItem>()

        currencyItemList.add(CurrencyItem("EUR", R.drawable.european_flag))
        currencyItemList.add(CurrencyItem("USD", R.drawable.usa_flag))
        currencyItemList.add(CurrencyItem("GBP", R.drawable.uk_flag))
        currencyItemList.add(CurrencyItem("NGN", R.drawable.nigerian_flag))
        currencyItemList.add(CurrencyItem("PLN", R.drawable.poland_flag))

        return currencyItemList
    }


    private fun populateToCurrencyItem(): ArrayList<CurrencyItem> {
        val currencyItemList = ArrayList<CurrencyItem>()
        currencyItemList.add(CurrencyItem("PLN", R.drawable.poland_flag))
        currencyItemList.add(CurrencyItem("EUR", R.drawable.european_flag))
        currencyItemList.add(CurrencyItem("USD", R.drawable.usa_flag))
        currencyItemList.add(CurrencyItem("GBP", R.drawable.uk_flag))
        currencyItemList.add(CurrencyItem("NGN", R.drawable.nigerian_flag))


        return currencyItemList
    }

    fun showErrorSnackBar(message:String){
        val snackBar= Snackbar.make(findViewById(android.R.id.content),message, Snackbar.LENGTH_LONG)
        val snackBarView=snackBar.view
        snackBarView.setBackgroundColor(ContextCompat.getColor(this,R.color.my_green)
        )
        snackBar.show()
    }
}