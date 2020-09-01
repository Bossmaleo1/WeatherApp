package com.dagger.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.ForecastAdapterBinding

class ForeCastListAdapter(private val foreCastPeriodList: ArrayList<ForeCastPeriodItem>) :  RecyclerView.Adapter<ForeCastListAdapter.ForeCastViewHolder>() {

    fun updateCityList(newForecastPerioList: List<ForeCastPeriodItem>){
        foreCastPeriodList.clear()
        foreCastPeriodList.addAll(newForecastPerioList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = foreCastPeriodList.size

    class ForeCastViewHolder(var view: ForecastAdapterBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForecastAdapterBinding>(inflater, R.layout.forecast_adapter,parent,false)
        return ForeCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        //holder.view.foreCastPeriodItem = foreCastPeriodList[position]
    }

}