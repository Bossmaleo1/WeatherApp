package com.dagger.weatherapp.presentation.fragment

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentCityMapBinding
import com.dagger.weatherapp.framework.model.entity.City
import com.dagger.weatherapp.presentation.fragment.CityMapFragmentArgs
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions


class CityMapFragment : Fragment(), OnMapReadyCallback {

    private lateinit var mMap: GoogleMap
    private var city: City? = null
    private  lateinit var binding : FragmentCityMapBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_city_map,container,false)

        //val view = inflater.inflate(R.layout.fragment_city_map, container, false)


        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        val actionbar = (activity as AppCompatActivity?)!!.supportActionBar

        //here we are enabled false a arrow back button
        actionbar!!.setDisplayHomeAsUpEnabled(true)
        //we config our map
        val mapOptions = GoogleMapOptions()
            .mapType(GoogleMap.MAP_TYPE_NORMAL)
            .zoomControlsEnabled(true)
            .zoomGesturesEnabled(true)

        val mapFragment = SupportMapFragment.newInstance(mapOptions)

        mapFragment.getMapAsync(this)

        //here we called a google map view
        activity!!.supportFragmentManager.beginTransaction()
            .replace(R.id.content,mapFragment)
            .commit()

        //we call our arguments
        arguments?.let {
            city = CityMapFragmentArgs.fromBundle(
                it
            ).cityitem
            actionbar!!.title =  city!!.cityName
        }

        // Inflate the layout for this fragment
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }



    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val mytown = city?.lat?.let { LatLng(it, city!!.long) }
        mMap.addMarker(mytown?.let { MarkerOptions().position(it).title("Our Town Map") })
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mytown,16f))
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            android.R.id.home -> {
                findNavController().popBackStack()
                Toast.makeText(context,"Test de MALEO-SAMA",Toast.LENGTH_LONG).show()
                return true
            }

        }

        return super.onOptionsItemSelected(item)
    }



}