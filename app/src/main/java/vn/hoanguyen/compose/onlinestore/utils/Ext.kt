package vn.hoanguyen.compose.onlinestore.utils

import vn.hoanguyen.compose.onlinestore.R
import vn.hoanguyen.compose.onlinestore.data_providers.CardType

fun String.toCardIcon(): Int =
    when (this) {
        CardType.VISA.type -> R.drawable.logo_visa
        CardType.JCB.type -> R.drawable.logo_jcb
        CardType.MasterCard.type -> R.drawable.logo_master_card
        CardType.AmericanExpress.type -> R.drawable.logo_american_express
        else -> R.drawable.logo_card
    }