package com.example.data.remote.dto


data class CurrentWeatherResponse(
    val location: Location,
    val current: Current
){
    data class Location(
        val name: String,
    )

    data class Current(
        val temp_c: Double,
        val temp_f: Double,
        val humidity :Int,
        val uv :Int,
        val feelslike_c: Double,
        val feelslike_f: Double,
        val condition: Condition
    ){
        data class Condition(
            val text: String,
            val icon: String
        )
    }
}