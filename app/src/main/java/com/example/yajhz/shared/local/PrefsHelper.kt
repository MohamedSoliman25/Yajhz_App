package com.example.yajhz.shared.local

import android.content.Context
import android.content.SharedPreferences
import com.example.yajhz.App
import com.example.yajhz.login.domain.model.Data
import com.example.yajhz.shared.util.Utils
import com.google.gson.Gson

object PrefsHelper {
    private const val PREFS_NAME = "yajhz"

    private fun getSharedPreferences(): SharedPreferences {
        return App.getAppContext().getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    fun putInt(key: String, value: Int) {
        getSharedPreferences().edit().putInt(key, value).apply()
    }

    fun getInt( key: String, defaultValue: Int): Int {
        return getSharedPreferences().getInt(key, defaultValue)
    }

    fun putBoolean( key: String, value: Boolean) {
        getSharedPreferences().edit().putBoolean(key, value).apply()
    }

    fun getBoolean( key: String, defaultValue: Boolean): Boolean {
        return getSharedPreferences().getBoolean(key, defaultValue)
    }

    fun putFloat( key: String, value: Float) {
        getSharedPreferences().edit().putFloat(key, value).apply()
    }

    fun getFloat( key: String, defaultValue: Float): Float {
        return getSharedPreferences().getFloat(key, defaultValue)
    }

    fun putLong( key: String, value: Long) {
        getSharedPreferences().edit().putLong(key, value).apply()
    }

    fun getLong(key: String, defaultValue: Long): Long {
        return getSharedPreferences().getLong(key, defaultValue)
    }

    fun putString(key: String, value: String) {
        getSharedPreferences().edit().putString(key, value).apply()
    }

    fun getString( key: String, defaultValue: String): String? {
        return getSharedPreferences().getString(key, defaultValue)
    }

    fun saveUserData(data: Data?) {
        val json: String?
        if (data != null) {
            val gson = Gson()
            json = gson.toJson(data)
            json?.let {
                putString(Utils.USER_DATA, it)
            }
        }
    }

    fun getUserData(): Data? {
        var user: Data? = null
        val gson = Gson()
        val json: String = getString(Utils.USER_DATA, "")!!
        if (json.isNotEmpty()) user = gson.fromJson(json, Data::class.java)
        return user
    }

    fun clear() {
        getSharedPreferences().edit().clear().apply()
    }

    fun remove( key: String) {
        getSharedPreferences().edit().remove(key).apply()
    }
}