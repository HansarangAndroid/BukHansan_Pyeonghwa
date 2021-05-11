package com.example.lecturesopt28th.signup.data.dto

data class ResponseSingUp(
    val success: Boolean,
    val message: String,
    val data: Data
) {
    data class Data(
        val nickname: String
    )
}