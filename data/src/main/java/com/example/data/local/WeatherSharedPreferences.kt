package com.example.data.local

import android.content.Context
import android.os.Build
import com.example.data.repository.model.CurrentWeather
import com.google.gson.Gson


class WeatherSharedPreferences(
    val context: Context
) {
   private val sharedPreferences =
       context.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)


    fun saveCurrentWeather(currentWeather: CurrentWeather) {
        val editor = sharedPreferences.edit()

        // Convert the object to JSON
        val gson = Gson()
        val json = gson.toJson(currentWeather)

        // Save the JSON string
        editor.putString(CURRENT_WEATHER_KEY, json)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.GINGERBREAD) {
            editor.apply()
        }
    }

    fun getWeatherCurrent(): CurrentWeather? {
        // Get the JSON string
        val gson = Gson()
        // Convert JSON back to object
        val json = sharedPreferences.getString(CURRENT_WEATHER_KEY, null) ?: return null

        return gson.fromJson(json, CurrentWeather::class.java)
    }

    companion object{
        private const val CURRENT_WEATHER_KEY = "current_weather"
        private const val SHARED_PREFERENCES_NAME = "my_preferences"
    }
}