package tn.org.data.utils

import java.text.SimpleDateFormat
import java.util.*

fun Date.toVersion(format: String = "yyyyMMdd", locale: Locale = Locale.getDefault()): String {
    val formatter = SimpleDateFormat(format, locale)
    return formatter.format(this)
}