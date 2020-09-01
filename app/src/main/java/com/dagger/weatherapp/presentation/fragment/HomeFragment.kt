package com.dagger.weatherapp.presentation.fragment

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.OrientationHelper
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentHomeBinding
import com.dagger.weatherapp.framework.model.entity.City
import com.dagger.weatherapp.presentation.adapter.ForeCastListAdapter
import com.dagger.weatherapp.framework.viewmodel.ForeCastPeriodViewModel
import com.dagger.weatherapp.framework.viewmodel.ForeCastPeriodViewModelFactory
import com.google.android.gms.maps.*
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() , OnMapReadyCallback {

    private lateinit var binding : FragmentHomeBinding
    private var city: City? = null
    private  var actionbar:ActionBar? = null
    private lateinit var viewModel: ForeCastPeriodViewModel
    private lateinit var viewModelFactory: ForeCastPeriodViewModelFactory
    private val foreCastListAdapter = ForeCastListAdapter(arrayListOf())

    private lateinit var mMap: GoogleMap

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //we initialize our city with New York

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        actionbar = (activity as AppCompatActivity?)!!.supportActionBar

        //here we are enabled false a arrow back button
        actionbar!!.setDisplayHomeAsUpEnabled(false)

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

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customOurCallBack()



        arguments?.let {
            city = HomeFragmentArgs.fromBundle(
                it
            ).cityitem
            //we set the town title
            actionbar!!.title =  city!!.cityName
        }

        viewModelFactory = ForeCastPeriodViewModelFactory(activity!!.application,city!!)
        //we called our viewmodel
        viewModel = ViewModelProvider(this,viewModelFactory).get(ForeCastPeriodViewModel::class.java)

        forecastList.apply{
            layoutManager = LinearLayoutManager(context,OrientationHelper.HORIZONTAL,false)
            adapter = foreCastListAdapter
        }

        observeMyViewModel()
    }

    //in this function we are observe our city list
    fun observeMyViewModel() {

        //we are call our forecasperiod livedata
        viewModel.foreCastPeriodeList.observe(this, Observer {foreCastPeriodList->
            foreCastPeriodList?.let {
                mainblock.visibility = View.VISIBLE
                shimmer_view_container.visibility = View.GONE
                foreCastListAdapter.updateCityList(foreCastPeriodList)
                Log.i("retrofit","done !!")
            }
        })


       /* viewModel.loading.observe(this, Observer { isLoading->
                isLoading?.let {
                    if(isLoading) {
                        mainblock.visibility = View.VISIBLE
                        shimmer_view_container.visibility = View.GONE
                    }
                }
        })*/


    }


    fun customOurCallBack() {
        //method to custom onBackPressed in our Fragment
        val callback: OnBackPressedCallback =
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    val intent = Intent(Intent.ACTION_MAIN)
                    intent.addCategory(Intent.CATEGORY_HOME)
                    intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TOP
                    startActivity(intent)
                    System.exit(0)
                }
            }

        requireActivity().getOnBackPressedDispatcher().addCallback(this, callback)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.home_menu,menu)
        //here we are change our icon color
        val myTownIcon : Drawable? = activity!!.getDrawable(R.drawable.baseline_location_city_black_24)
        myTownIcon!!.setColorFilter(activity!!.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
        menu!!.findItem(R.id.mytown).icon = myTownIcon
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.mytown -> {
                val action =
                    HomeFragmentDirections.actionHomeFragmentToChooseCityFragment()
                action.cityitem = city
                findNavController().navigate(action)
            }

        }

        return super.onOptionsItemSelected(item)
    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap

        val mytown = city?.lat?.let { LatLng(it, city!!.long) }
        mMap.addMarker(mytown?.let { MarkerOptions().position(it).title("Our Town Map") })
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(mytown,16f))
    }


}