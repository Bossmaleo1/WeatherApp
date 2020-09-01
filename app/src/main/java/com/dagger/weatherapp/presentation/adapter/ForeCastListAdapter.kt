package com.dagger.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.dagger.core.data.ForeCastPeriodItem
import com.dagger.weatherapp.R
import com.dagger.weatherapp.framework.util.loadImage
import kotlinx.android.synthetic.main.forecast_adapter.view.*

class ForeCastListAdapter(private val foreCastPeriodList: ArrayList<ForeCastPeriodItem>) :  RecyclerView.Adapter<ForeCastListAdapter.ForeCastViewHolder>() {

    fun updateCityList(newForecastPerioList: List<ForeCastPeriodItem>){
        foreCastPeriodList.clear()
        foreCastPeriodList.addAll(newForecastPerioList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = foreCastPeriodList.size

    inner class ForeCastViewHolder(view: View): RecyclerView.ViewHolder(view) {
        private val  day = view.day
        private val timeperiods = view.timeperiods
        private val temperature= view.temperature
        private val windSpeed = view.windSpeed
        private val shortforecast = view.shortforecast
        private val detailedForecast = view.detailedForecast
        private val icon = view.icon

        fun bind(foreCastPeriodItem:  ForeCastPeriodItem) {
            day.text = foreCastPeriodItem.name
            timeperiods.text = foreCastPeriodItem.endTime
            temperature.text = foreCastPeriodItem.temperature.toString()
            windSpeed.text = foreCastPeriodItem.windSpeed
            shortforecast.text = foreCastPeriodItem.shortForecast
            detailedForecast.text = foreCastPeriodItem.detailedForecast
            loadImage(icon,foreCastPeriodItem.icon)
        }
    }



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ForeCastViewHolder (
        LayoutInflater.from(parent.context).inflate(R.layout.forecast_adapter,parent,false)
    )

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        //holder.view.foreCastPeriodItem = foreCastPeriodList[position]
        holder.bind(foreCastPeriodList[position])
    }

}