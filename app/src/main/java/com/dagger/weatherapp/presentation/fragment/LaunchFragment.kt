package com.dagger.weatherapp.presentation.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentLaunchBinding
import com.dagger.weatherapp.framework.model.entity.City
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


class LaunchFragment : Fragment() {

    private lateinit var binding :FragmentLaunchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater,R.layout.fragment_launch,container,false)
        // Inflate the layout for this fragment
        return binding.root
    }

    private fun NavigationRoot() {
        CoroutineScope(Dispatchers.Main) .launch {
            delay(100)
            //val bundle = bundleOf("user_input" to  binding.editText.text.toString())
            val action =
                LaunchFragmentDirections.actionLaunchFragmentToHomeFragment()
            action.cityitem = City(
                0,
                "New York",
                40.712776,
                -74.005974,32,
                34
            )
            //Navigation.findNavController(v).navigate(action)
            findNavController().navigate(action)
        }
    }

    override fun onStart() {
        super.onStart()
        NavigationRoot()
    }


}