package com.example.uf1_proyecto.Manager
import android.content.Context
import android.content.SharedPreferences

class LanguageManager(context: Context) {

    private val prefs: SharedPreferences = context.getSharedPreferences("language", Context.MODE_PRIVATE)

    fun setLocale(localeCode: String) {
        val editor = prefs.edit()
        editor.putString("selectedLanguage", localeCode)
        editor.apply()

        updateResources(localeCode)
    }

    private fun updateResources(language: String) {
        val locale = java.util.Locale(language)
        java.util.Locale.setDefault(locale)
        val config = android.content.res.Configuration()
        config.locale = locale
        config.setLayoutDirection(locale)
        android.content.res.Resources.getSystem().updateConfiguration(config, android.content.res.Resources.getSystem().displayMetrics)
    }

    fun getCurrentLanguage(): String {
        return prefs.getString("selectedLanguage", "en") ?: "en"
    }
}
