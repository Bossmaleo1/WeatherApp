package com.dagger.weatherapp.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.ForecastAdapterBinding
import com.dagger.weatherapp.framework.model.entity.ForeCastPeriodItemEntity

class ForeCastListAdapter(val foreCastPeriodListEntity: ArrayList<ForeCastPeriodItemEntity>) :  RecyclerView.Adapter<ForeCastListAdapter.ForeCastViewHolder>() {

    fun updateCityList(newForecastPerioList: List<ForeCastPeriodItemEntity>){
        foreCastPeriodListEntity.clear()
        foreCastPeriodListEntity.addAll(newForecastPerioList)
        notifyDataSetChanged()
    }

    override fun getItemCount() = foreCastPeriodListEntity.size

    class ForeCastViewHolder(var view: ForecastAdapterBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ForeCastViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ForecastAdapterBinding>(inflater, R.layout.forecast_adapter,parent,false)
        return ForeCastViewHolder(view)
    }

    override fun onBindViewHolder(holder: ForeCastViewHolder, position: Int) {
        holder.view.foreCastPeriodItem = foreCastPeriodListEntity[position]
    }

}