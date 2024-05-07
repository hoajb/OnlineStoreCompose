package vn.hoanguyen.compose.onlinestore.data_providers

enum class CardType(val type: String) {
    AmericanExpress("American Express"),
    JCB("JCB"),
    VISA("Visa"),
    MasterCard("MasterCard"),
}

data class CardInfo(
    val name: String,
    val number: String,
)
