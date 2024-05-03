package vn.hoanguyen.compose.onlinestore.data_providers

data class Product(
    val discount: Int,
    val id: String,
    val name: String,
    val description: String,
    val price: Double,
    val imageUrl: String,
    val category: String,

    val sizeList: List<String>
)