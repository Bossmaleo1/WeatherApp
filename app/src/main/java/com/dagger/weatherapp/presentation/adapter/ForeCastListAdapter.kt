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
        private val timestarted = view.timestarted
        private val timeend = view.timeend
        private val temperature= view.temperature
        private val windSpeed = view.windSpeed
        private val shortforecast = view.shortforecast
        private val detailedForecast = view.detailedForecast
        private val icon = view.icon

        fun bind(foreCastPeriodItem:  ForeCastPeriodItem) {
            day.text = foreCastPeriodItem.name


            timestarted.text = "Started : "+foreCastPeriodItem.startTime.toString().split("T")[0]+
                    " between "+foreCastPeriodItem.startTime.toString().split("T")[1].split("-")[0]+
                    " and "+foreCastPeriodItem.startTime.toString().split("T")[1].split("-")[1]

            //
            timeend.text = "Finished : "+foreCastPeriodItem.endTime.toString().split("T")[0]+
            " between "+foreCastPeriodItem.endTime.toString().split("T")[1].split("-")[0]+
                    " and "+foreCastPeriodItem.endTime.toString().split("T")[1].split("-")[1]

            temperature.text = foreCastPeriodItem.temperature.toString()+" "+foreCastPeriodItem.temperatureUnit
            windSpeed.text = "The wind speed is "+foreCastPeriodItem.windSpeed
            shortforecast.text = foreCastPeriodItem.shortForecast
            detailedForecast.text =  foreCastPeriodItem.detailedForecast
            loadImage(icon,foreCastPeriodItem.icon)
        }

        fun splitTheDate(data : String) {

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