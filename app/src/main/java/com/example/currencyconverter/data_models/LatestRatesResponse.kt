package com.example.currencyconverter.data_models

import java.io.Serializable

data class LatestRatesResponse(val success:Boolean,
                               val timestamp:Int,
                               val base:String,
                               val date:String,
                               val rates:Rates
                                  ) :Serializable{




}