package vn.hoanguyen.compose.onlinestore.data_providers

data class Notification(
    val title: String,
    val content: String,
    val type: Int,
    val timestamp: Long,
)
