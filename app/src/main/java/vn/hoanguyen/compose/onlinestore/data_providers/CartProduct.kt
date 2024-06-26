package vn.hoanguyen.compose.onlinestore.data_providers

data class CartProduct(
    val product: Product,
    val size: String,
    val quantity: Int
)

data class OrderProduct(
    val cartProduct: CartProduct,
    val status: String
)