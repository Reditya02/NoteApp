package com.example.noteapp.data

import android.content.Context

class LoginPreferences(context: Context) {
    private val prefName = "pref"
    private val loginKey = "login"
    private val emailKey = "email"
    private var preferences = context.getSharedPreferences(prefName, Context.MODE_PRIVATE)

    var isLogin: Boolean
        get() {
            return preferences.getBoolean(loginKey, false)
        }
        set(value) {
            preferences.edit().apply {
                putBoolean(loginKey, value)
                apply()
            }
        }

    var loginEmail: String
        get() {
            return preferences.getString(emailKey, "") ?: ""
        }
        set(value) {
            preferences.edit().apply {
                putString(emailKey, value)
                apply()
            }
        }
}