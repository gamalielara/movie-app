package com.example.data

data class User (
    var username: String?=null,
    var password: String?=null,
    var email: String?=null,
    var isLoggedIn: Boolean = username == null,
)