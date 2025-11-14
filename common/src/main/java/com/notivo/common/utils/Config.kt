package com.notivo.common.utils

import com.pixplicity.easyprefs.library.Prefs

object Config {
    private const val IS_TABLET = "isTablet"
    var isTablet: Boolean
        get() = getBooleanOrDefault(IS_TABLET)
        set(value) = Prefs.putBoolean(IS_TABLET, value)


    private fun getBooleanOrDefault(key: String): Boolean {
        return if (isPreview) {
            // In preview mode, return sensible defaults to avoid Prefs initialization issues
            when (key) {
                Config.IS_TABLET -> false  // Default to phone layout in previews
                else -> false
            }
        } else {
            Prefs.getBoolean(key, false)
        }
    }
}