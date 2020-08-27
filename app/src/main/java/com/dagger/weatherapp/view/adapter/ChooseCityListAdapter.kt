package com.dagger.weatherapp.view.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.recyclerview.widget.RecyclerView
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.ChooseCityAdapterBinding
import com.dagger.weatherapp.model.entity.City
import com.dagger.weatherapp.view.fragment.ChooseCityListFragmentDirections
import com.dagger.weatherapp.view.fragment.HomeFragmentDirections
import com.dagger.weatherapp.view.listener.CityListener
import kotlinx.android.synthetic.main.choose_city_adapter.view.*

class ChooseCityListAdapter(val cities: ArrayList<City>) :  RecyclerView.Adapter<ChooseCityListAdapter.ChooseCityViewHolder>(),
    CityListener {


    fun updateCityList(newCityList: List<City>){
        cities.clear()
        cities.addAll(newCityList)
        notifyDataSetChanged()
    }

    class ChooseCityViewHolder(var view: ChooseCityAdapterBinding) : RecyclerView.ViewHolder(view.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChooseCityViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val view = DataBindingUtil.inflate<ChooseCityAdapterBinding>(inflater, R.layout.choose_city_adapter,parent,false)
        return ChooseCityViewHolder(view)
    }

    override fun getItemCount() = cities.size

    override fun onBindViewHolder(holder: ChooseCityViewHolder, position: Int) {
        holder.view.city = cities[position]
        holder.view.listener = this
    }

    override fun onCityClicked(v: View) {
        val position = v.cityid.text.toString().toInt()
        val action = ChooseCityListFragmentDirections.actionChooseCityFragmentToHomeFragment()
        action.cityitem = cities[position]
        Navigation.findNavController(v).navigate(action)
    }
}