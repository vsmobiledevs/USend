package com.dhaw.customer.utils

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build
import java.util.*
import com.usend.utils.PreferenceHelper.customPrefs

/**
 * Created by Pritesh Vadhiya on 4/7/18.
 * Spaceo Technologies
 * priteshv.spaceo@gmail.com
 */
object LocaleManager {

    const val LANGUAGE_ENGLISH = "en"
    const val LANGUAGE_ARABIC = "ar"
    const val LANGUAGE_URDU = "ur"

    private const val LANGUAGE_KEY = "language_key"

    fun setLocale(c: Context) {
        updateResources(c, getLanguage(c))
    }

    fun setNewLocale(c: Activity, language: String) {
        persistLanguage(c, language)
        updateResources(c, language)
    }

    fun getLanguage(c: Context): String {
        val prefs = c.customPrefs()
        return prefs.getString(LANGUAGE_KEY, LANGUAGE_ENGLISH)!!
    }

    @SuppressLint("ApplySharedPref")
    private fun persistLanguage(c: Context, language: String) {
        val prefs = c.customPrefs()
        // use commit() instead of apply(), because sometimes we kill the application process immediately
        // which will prevent apply() to finish
        prefs.edit().putString(LANGUAGE_KEY, language).commit()
    }

    private fun updateResources(context: Context, language: String) {
        var context = context
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLayoutDirection(Locale(language))
        config.locale = Locale.getDefault()
        res.updateConfiguration(config, res.displayMetrics)
    }

    fun getLocale(res: Resources): Locale {
        val config = res.configuration
        return if (Build.VERSION.SDK_INT >= 24) config.locales.get(0) else config.locale
    }
}