package vn.hoanguyen.compose.onlinestore.utils

fun CharSequence?.isValidEmail() =
    !isNullOrEmpty() && Regex("^\\w+([.-]?\\w+)*@\\w+([.-]?\\w+)*(\\.\\w{2,3})+$").matches(this)

fun CharSequence?.isValidPassword() = !isNullOrEmpty() && Regex("\\w{6,}").matches(this)