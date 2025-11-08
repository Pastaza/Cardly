package com.cardly.manual

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

object Storage {
    private const val KEY = "cards_json"

    fun saveCards(context: Context, list: List<Card>) {
        val prefs = context.getSharedPreferences("cardly", Context.MODE_PRIVATE)
        val json = Gson().toJson(list)
        prefs.edit().putString(KEY, json).apply()
    }

    fun loadCards(context: Context): MutableList<Card> {
        val prefs = context.getSharedPreferences("cardly", Context.MODE_PRIVATE)
        val json = prefs.getString(KEY, null) ?: return mutableListOf()
        val type = object : TypeToken<MutableList<Card>>() {}.type
        return Gson().fromJson(json, type)
    }
}
