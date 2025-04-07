package com.example.zodiaco.utils

import android.content.Context

class SessionManager(context: Context) {
    private val sharedPref = context.getSharedPreferences("zodiak_session", Context.MODE_PRIVATE)

    fun  setFavoritoHoroscopo(id: String) {
        val editor = sharedPref.edit()
        editor.putString("FOVORITO_HOROSCOPO", id)
        editor.apply()
    }

    fun getFavoritoHoroscopo(): String {
       return sharedPref.getString("FOVORITO_HOROSCOPO", "")!!
    }
}