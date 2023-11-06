package com.msicoding.lproject.presention.sign_in


data class SignInResult (
    val data: UserData?,
    val errorMessage: String?
)

data class UserData (
    val userID: String,
    val userName: String,
    val profilePictureUrl: String?
)