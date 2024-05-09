package vn.hoanguyen.compose.onlinestore.data_providers

data class FAQ(
    val title: String,
    val content: String,
    val type: Int,
)

enum class FAQType(val value: Int) {
    General(0),
    Account(1),
    Service(2),
    Payment(3),
}

fun Int.toFAQType(): FAQType {
    return when (this) {
        0 -> FAQType.General
        1 -> FAQType.Account
        2 -> FAQType.Service
        3 -> FAQType.Payment
        else -> FAQType.General
    }
}