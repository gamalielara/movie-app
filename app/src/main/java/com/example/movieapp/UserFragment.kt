package com.example.movieapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.model.User
import kotlinx.android.synthetic.main.fragment_user.*


class UserFragment : Fragment() {
    private lateinit var sharedPreference: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreference =
            requireActivity().getSharedPreferences("userDetail", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_user, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupUI()
        buttonEventsHandler()
    }

    private fun setupUI() {
        val username = sharedPreference.getString("user_username", "")
        val email = sharedPreference.getString("user_email", "")
        val password = sharedPreference.getString("user_password", "")

        helloUserName.text = "Hello, $username!"

        editUsername.setText(username)
        editEmail.setText(email)
        editPassword.setText(password)
    }

    private fun buttonEventsHandler() {
        val editor = sharedPreference.edit()

        var user = User(editUsername.text.toString(), editPassword.text.toString(), editEmail.text.toString(), true)

        logoutButton.setOnClickListener {
            editor.clear()
            editor.apply()

            Toast.makeText(activity, "Logout is successful", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_userFragment_to_homeFragment)
        }

        editUsername.doOnTextChanged { text, _, _, _ -> user.username = text.toString() }
        editEmail.doOnTextChanged { text, _, _, _ -> user.email = text.toString() }
        editPassword.doOnTextChanged { text, _, _, _ -> user.password = text.toString() }

        editProfileButton.setOnClickListener {
            editor.putString("user_username", user.username)
            editor.putString("user_email", user.email)
            editor.putString("user_password", user.password)
            editor.apply()

            Toast.makeText(activity, "Profile is successfully updated!", Toast.LENGTH_LONG).show()

            findNavController().navigate(R.id.action_userFragment_to_homeFragment)
        }
    }
}