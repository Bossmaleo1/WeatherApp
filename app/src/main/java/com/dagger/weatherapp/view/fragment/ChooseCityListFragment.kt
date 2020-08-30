package com.dagger.weatherapp.view.fragment

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentChooseCityBinding
import com.dagger.weatherapp.model.entity.City
import com.dagger.weatherapp.view.adapter.ChooseCityListAdapter
import com.dagger.weatherapp.viewmodel.ListCityViewModel
import kotlinx.android.synthetic.main.fragment_choose_city.*


class ChooseCityListFragment : Fragment(){

    private  lateinit var binding : FragmentChooseCityBinding
    private lateinit var viewModel: ListCityViewModel
    private val chooseCityListAdapter = ChooseCityListAdapter(arrayListOf())
    private var city: City? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_choose_city,container,false)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        val actionbar = (activity as AppCompatActivity?)!!.supportActionBar

        //here we are enabled false a arrow back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        //here we set the toolbar title
        actionbar!!.title =  activity!!.resources.getString(R.string.choose_city)

        // Inflate the layout for this fragment
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        //we called our viewmodel
        viewModel = ViewModelProvider(this).get(ListCityViewModel::class.java)

        cityList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = chooseCityListAdapter
        }

        arguments?.let {
            city = ChooseCityListFragmentArgs.fromBundle(it).cityitem
        }

        observeMyViewModel()
    }


    //in this function we are observe our city list
    fun observeMyViewModel() {
        viewModel.cities.observe(this, Observer {cities->
            cities.let {
                chooseCityListAdapter.updateCityList(cities)
            }
        })
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home -> {
                findNavController().popBackStack()
                //Navigation.findNavController().popBackStack()
                /*val action = ChooseCityListFragmentDirections.actionChooseCityFragmentToHomeFragment()
                action.cityitem = city
                findNavController().navigate(action)*/
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }

}