package com.example.currencyconverter.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ImageView
import android.widget.TextView
import com.example.currencyconverter.R

class CurrencySpinnerAdapter(context:Context,val currencyItemList:ArrayList<CurrencyItem>) :
    ArrayAdapter<CurrencyItem> (context,0,currencyItemList){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        var mView=convertView
        if(mView==null){
          mView=LayoutInflater.from(context).inflate(R.layout.custom_currency_spinner,parent,false)

        }
val item=getItem(position)



        if(item!=null){
            val spinnerImageView=mView!!.findViewById<ImageView>(R.id.spinner_icon)
            val spinnerTextView=mView!!.findViewById<TextView>(R.id.tv_item_spinner)

            spinnerImageView.setImageResource(item!!.currencyImage)
            spinnerTextView.text=item!!.currencyName




        }


        return mView!!
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup): View {
        var mView=convertView
        if(mView==null){
            mView=LayoutInflater.from(context).inflate(R.layout.custom_spinner_dropdown_item,parent,false)

        }
        val item=getItem(position)



        if(item!=null){
            val spinnerImageView=mView!!.findViewById<ImageView>(R.id.spinner_iconDrop)
            val spinnerTextView=mView!!.findViewById<TextView>(R.id.tv_item_spinnerDrop)

            spinnerImageView.setImageResource(item!!.currencyImage)
            spinnerTextView.text=item!!.currencyName




        }


        return mView!!


    }

}