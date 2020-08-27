package com.dagger.weatherapp.view.fragment

import android.content.Intent
import android.graphics.PorterDuff
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.annotation.RequiresApi
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentHomeBinding
import com.dagger.weatherapp.model.entity.City


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding
    private var city: City? = null
    private  var actionbar:ActionBar? = null

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
        //here we set the town title
        //actionbar!!.title =  city?.cityName

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customOurCallBack()

        arguments?.let {
            city = HomeFragmentArgs.fromBundle(it).cityitem
            //we set the town title
            actionbar!!.title =  city!!.cityName
        }
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
        val myTownIcon : Drawable? = activity!!.getDrawable(R.drawable.baseline_location_city_black_24)
        myTownIcon!!.setColorFilter(activity!!.getColor(R.color.white), PorterDuff.Mode.SRC_IN)
        menu!!.findItem(R.id.mytown).icon = myTownIcon
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){

            R.id.mytown -> {
                val action = HomeFragmentDirections.actionHomeFragmentToChooseCityFragment()
                action.cityitem = city
                findNavController().navigate(action)
            }
        }

        return super.onOptionsItemSelected(item)
    }


}