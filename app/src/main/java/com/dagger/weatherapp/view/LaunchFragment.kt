package com.dagger.weatherapp.view

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.dagger.weatherapp.R
import com.dagger.weatherapp.databinding.FragmentLaunchBinding
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
            findNavController().navigate(R.id.action_launchFragment_to_homeFragment)
        }
    }

    override fun onStart() {
        super.onStart()
        NavigationRoot()
    }


}