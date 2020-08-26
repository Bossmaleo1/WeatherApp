package com.dagger.weatherapp.view

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
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentHomeBinding


class HomeFragment : Fragment() {

    private lateinit var binding : FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {


        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_home,container,false)

        setHasOptionsMenu(true)

        (activity as AppCompatActivity?)!!.setSupportActionBar(binding.toolbar)
        val actionbar = (activity as AppCompatActivity?)!!.supportActionBar

        //here we are enabled false a arrow back button
        actionbar!!.setDisplayHomeAsUpEnabled(false)
        //here we set the town title
        actionbar!!.title =  "Chicago"

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        customOurCallBack()
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

            }
        }

        return super.onOptionsItemSelected(item)
    }


}