package vn.hoanguyen.compose.onlinestore.features.manament.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.data_providers.Notification
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun NotificationsScreen(
    onBack: () -> Unit, viewmodel: NotificationsViewmodel = hiltViewModel()
) {
    val notificationsList = viewmodel.listNotifications.collectAsState()
    LaunchedEffect(key1 = Unit) {
        viewmodel.loadNotificationList()
    }

    NotificationsBody(
        onBack = onBack,
        notificationsList = notificationsList.value
    )

}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationsBody(
    onBack: () -> Unit,
    notificationsList: List<NotificationGroup>
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "Notifications", onBack = onBack)
    }) { padding ->
        LazyColumn(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            itemsIndexed(notificationsList) { index, notificationGroup ->
                Column(
                    modifier = Modifier.padding(vertical = 8.dp)
                ) {
                    NotificationGroupedItem(notificationGroup = notificationGroup)

                    if (index < notificationsList.size - 1)
                        AppDivider()
                }
            }
        }
    }
}

@Composable
private fun NotificationGroupedItem(
    modifier: Modifier = Modifier,
    notificationGroup: NotificationGroup
) {
    Column(
        modifier = modifier.padding(vertical = 8.dp)
    ) {
        Text(
            text = notificationGroup.date,
            style = AppTypography.titleSmall
        )

        Spacer(modifier = Modifier.height(8.dp))

        notificationGroup.notifications.forEachIndexed() { index, notificationItem ->
            Column(
                modifier = Modifier.padding(vertical = 8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        modifier = Modifier.size(28.dp),
                        imageVector = notificationItem.type.toNotificationIcon(),
                        contentDescription = "notification_icon"
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Column {
                        Text(
                            text = notificationItem.title,
                            style = AppTypography.bodyMedium
                        )

                        Spacer(modifier = Modifier.height(4.dp))

                        Text(
                            text = notificationItem.content,
                            style = AppTypography.bodySmall.copy(color = Color.Gray)
                        )
                    }
                }
                Spacer(modifier = Modifier.height(8.dp))
                if (index < notificationGroup.notifications.size - 1)
                    AppDivider(modifier = Modifier.padding(start = 36.dp))
            }
        }
    }
}

@Preview
@Composable
private fun NotificationsScreenPrev() {
    OnlineStoreComposeTheme {
        NotificationsBody(
            onBack = {},
            listOf(
                NotificationGroup(
                    date = "Today",
                    notifications = listOf(
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 0,
                            timestamp = 1715227585766,
                        ),
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 1,
                            timestamp = 1715227585766,
                        ),
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 2,
                            timestamp = 1715227585766,
                        ),
                    )
                ),
                NotificationGroup(
                    date = "June 7, 2023",
                    notifications = listOf(
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 0,
                            timestamp = 1715227585766,
                        ),
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 1,
                            timestamp = 1715227585766,
                        ),
                        Notification(
                            title = "30% Special Discount!",
                            content = "Special promotion only valid today.",
                            type = 2,
                            timestamp = 1715227585766,
                        ),
                    )
                )
            )
        )
    }
}