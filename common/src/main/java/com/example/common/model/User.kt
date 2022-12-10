package com.example.common.model

data class User(
    var username: String?=null,
    var password: String?=null,
    var email: String?=null,
    var isLoggedIn: Boolean = username == null,
)