package vn.hoanguyen.compose.onlinestore.components.phonetextfield.data.utils

import android.content.Context
import vn.hoanguyen.compose.onlinestore.R
import vn.hoanguyen.compose.onlinestore.components.phonetextfield.data.CountryData

internal fun List<CountryData>.searchCountry(key: String, context: Context): List<CountryData> =
    this.mapNotNull { countryData ->
        countryNames[countryData.countryIso]?.let { countryName ->
            val localizedCountryName = context.resources.getString(countryName).lowercase()
            if (localizedCountryName.contains(key.lowercase())) {
                countryData to localizedCountryName
            } else {
                null
            }
        }
    }
        .partition { it.second.startsWith(key.lowercase()) }
        .let { (startWith, contains) ->
            startWith.map { it.first } + contains.map { it.first }
        }

internal fun List<CountryData>.sortedByLocalizedName(context: Context): List<CountryData> =
    this.sortedBy {
        context.resources.getString(
            countryNames.getOrDefault(it.countryIso, R.string.unknown),
        )
    }