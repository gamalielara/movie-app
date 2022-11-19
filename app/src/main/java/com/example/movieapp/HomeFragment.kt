package com.example.movieapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment() {
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference = requireActivity().getSharedPreferences("userDetail", Context.MODE_PRIVATE)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        if(!sharedPreference.getBoolean("is_logged_in", false)) {
            findNavController().navigate(R.id.action_homeFragment_to_loginFragment)
        } else {
            return inflater.inflate(R.layout.fragment_home, container, false)
        }
        return null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
    }

    private fun setupUI(){
        val username = sharedPreference.getString("user_username", "")
        helloUsername.text = "Hello, $username!"

        myProfileButton.setOnClickListener{
            findNavController().navigate(R.id.action_homeFragment_to_userFragment)
        }


    }
}