package com.example.movieapp

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.movieapp.model.User
import kotlinx.android.synthetic.main.fragment_login.*


class LoginFragment : Fragment() {
    private var user: User = User()

    private lateinit var sharedPreferences: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedPreferences =
            requireActivity().getSharedPreferences("userDetail", Context.MODE_PRIVATE)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        this.setupUI()
    }

    private fun setupUI() {
        if (sharedPreferences.getBoolean("is_logged_in", false)) {
            findNavController().navigate(R.id.action_loginFragment_to_home_nav)
        }

        var editor = sharedPreferences.edit()

        addEmail.doOnTextChanged { text, _, _, _ -> user.email = text.toString() }
        addPassword.doOnTextChanged { text, _, _, _ -> user.password = text.toString() }

        loginButton.setOnClickListener {
            if (user.email != null && user.password != null) {
                user.isLoggedIn = true

                editor.putString("user_email", user.email)
                editor.putString("user_password", user.password)
                editor.putBoolean("is_logged_in", user.isLoggedIn)
                editor.apply()

                findNavController().navigate(R.id.action_loginFragment_to_home_nav)
            } else {
                Toast.makeText(
                    activity,
                    "Sorry, you have to enter email and password first",
                    Toast.LENGTH_LONG
                ).show()
            }
        }
    }
}