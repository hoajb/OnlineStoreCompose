package vn.hoanguyen.compose.onlinestore.features.manament.notification

import android.util.Log
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.AccountCircle
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.outlined.Sell
import androidx.compose.material.icons.outlined.Wallet
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Notification
import vn.hoanguyen.compose.onlinestore.data_providers.UserService
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale
import javax.inject.Inject

data class NotificationGroup(
    val date: String,
    val notifications: List<Notification>,
)

@HiltViewModel
class NotificationsViewmodel @Inject constructor(
    private val userService: UserService
) : ViewModel() {
    private val _listNotifications = MutableStateFlow<List<NotificationGroup>>(emptyList())
    val listNotifications = _listNotifications.asStateFlow()
    private val sdf = SimpleDateFormat("MMMM d, yyyy", Locale.getDefault())

    fun loadNotificationList() {
        viewModelScope.launch {
            _listNotifications.emit(mapToNotificationGroups(userService.getNotificationsList()))
        }
    }

    private fun mapToNotificationGroups(notifications: List<Notification>): List<NotificationGroup> {
        val groupedNotifications = mutableMapOf<String, MutableList<Notification>>()

        notifications.forEach { notification ->
            val date = Date(notification.timestamp)
            Log.d("NotificationsViewmodel", notification.timestamp.toString())
            Log.d("NotificationsViewmodel", date.toString())
            val dayKey = sdf.format(date)
            Log.d("NotificationsViewmodel", dayKey.toString())

            if (groupedNotifications.containsKey(dayKey)) {
                groupedNotifications[dayKey]!!.add(notification)
            } else {
                groupedNotifications[dayKey] = mutableListOf(notification)
            }
        }

        val today = sdf.format(Calendar.getInstance().time)

        return groupedNotifications.map { (date, notifications) ->
            if (today == date) {
                NotificationGroup("Today", notifications)
            } else
                NotificationGroup(date, notifications)
        }
    }
}

fun Int.toNotificationIcon(): ImageVector {
    return when (this) {
        0 -> Icons.Outlined.Sell
        1 -> Icons.Outlined.Wallet
        2 -> Icons.Outlined.LocationOn
        3 -> Icons.Outlined.CreditCard
        4 -> Icons.Outlined.AccountCircle
        else -> Icons.Outlined.Notifications
    }
}
