package by.bsut.studapp.preferences

import android.content.SharedPreferences

open class SimplePreferenceEditor(private val preferences: SharedPreferences) {
    protected fun setBoolean(key: String, value: Boolean) {
        preferences.edit().apply {
            putBoolean(key, value)
            apply()
        }
    }

    protected fun setInt(key: String, value: Int) {
        preferences.edit().apply {
            putInt(key, value)
            apply()
        }
    }

    protected fun setString(key: String, value: String) {
        preferences.edit().apply {
            putString(key, value)
            apply()
        }
    }
}