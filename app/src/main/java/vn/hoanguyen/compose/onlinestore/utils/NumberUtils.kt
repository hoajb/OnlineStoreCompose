package vn.hoanguyen.compose.onlinestore.utils

import java.text.NumberFormat
import java.util.Locale

fun Number.formatAsCurrency(locale: Locale = Locale.getDefault()): String {
    val format = NumberFormat.getCurrencyInstance(locale)
    return format.format(this)
}
