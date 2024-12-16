package com.example.data.util

import org.json.JSONObject
import retrofit2.HttpException

// Function to extract the message from the error body
fun HttpException.extractErrorMessage(): String {
    return try {
        val errorBody = this.response()?.errorBody() ?: return "Opps! something went wrong"
        if (errorBody.toString().isNotBlank()) {
            val jsonObject = JSONObject(errorBody.toString())
            val message = jsonObject.getJSONObject("error").getString("message")
            message
        } else {
            "Opps! something went wrong"
        }
    } catch (exception: Exception) {
        // Handle any JSON parsing issues
        "Opps! something went wrong"
    }
}
