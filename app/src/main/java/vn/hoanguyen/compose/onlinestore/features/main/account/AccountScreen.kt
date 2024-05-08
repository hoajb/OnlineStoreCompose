package vn.hoanguyen.compose.onlinestore.features.main.account

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ChevronRight
import androidx.compose.material.icons.outlined.Badge
import androidx.compose.material.icons.outlined.HelpOutline
import androidx.compose.material.icons.outlined.Inventory2
import androidx.compose.material.icons.outlined.Logout
import androidx.compose.material.icons.outlined.NotificationsActive
import androidx.compose.material.icons.outlined.OtherHouses
import androidx.compose.material.icons.outlined.Payment
import androidx.compose.material.icons.outlined.SupportAgent
import androidx.compose.material3.Divider
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeAppBar
import vn.hoanguyen.compose.onlinestore.navigation.NavRoute
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorRed
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun AccountScreen(
    onNavigateMenu: (path: String) -> Unit,
    onLogout: () -> Unit
) {
    Scaffold(
        Modifier.background(Color.White),
        topBar = {
            HomeAppBar(title = "My Cart", onNavigateNotification = {})
        }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .background(color = Color.White)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
        ) {
            MenuRow(icon = Icons.Outlined.Inventory2,
                title = "My Orders",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.MyOrders.path
                    )
                })

            BlockDivider()

            MenuRow(icon = Icons.Outlined.Badge,
                title = "My Details",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.MyDetails.path
                    )
                })
            RowDivider()

            MenuRow(icon = Icons.Outlined.OtherHouses,
                title = "Address Book",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.AddressBook.path
                    )
                })
            RowDivider()

            MenuRow(icon = Icons.Outlined.Payment,
                title = "Payment Methods",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.PaymentMethods.path
                    )
                })
            RowDivider()

            MenuRow(icon = Icons.Outlined.NotificationsActive,
                title = "Notifications",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.NotificationSetting.path
                    )
                })
            BlockDivider()

            MenuRow(icon = Icons.Outlined.HelpOutline,
                title = "FAQs",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.FAQS.path
                    )
                })
            RowDivider()

            MenuRow(icon = Icons.Outlined.SupportAgent,
                title = "Help Center",
                onNavigate = {
                    onNavigateMenu.invoke(
                        NavRoute.HelpCenter.path
                    )
                })

            BlockDivider()

            LogoutRow(onNavigate = onLogout)

        }
    }
}

@Composable
fun RowDivider() {
    Divider(
        modifier = Modifier.padding(start = 50.dp, end = 24.dp),
        color = Color.Gray.copy(alpha = 0.3f)
    )
}

@Composable
fun BlockDivider() {
    Divider(
        thickness = 8.dp,
        color = Color.Gray.copy(alpha = 0.2f)
    )
}

@Composable
fun MenuRow(
    icon: ImageVector,
    title: String,
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onNavigate.invoke()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = icon, contentDescription = title)
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = title,
            style = AppTypography.bodyMedium.copy(color = Color.Gray)
        )
        Spacer(Modifier.width(10.dp))
        Icon(
            modifier = Modifier.size(32.dp),
            imageVector = Icons.Default.ChevronRight,
            tint = Color.LightGray,
            contentDescription = "navigate"
        )
    }
}

@Composable
fun LogoutRow(
    onNavigate: () -> Unit
) {
    Row(
        modifier = Modifier
            .clickable {
                onNavigate.invoke()
            }
            .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(imageVector = Icons.Outlined.Logout, tint = ColorRed, contentDescription = "logout")
        Spacer(Modifier.width(10.dp))
        Text(
            modifier = Modifier.weight(1f),
            text = "Logout",
            style = AppTypography.bodyMedium.copy(color = ColorRed)
        )
    }
}


@Preview
@Composable
fun AccountScreenPrev() {
    OnlineStoreComposeTheme {
        AccountScreen(onNavigateMenu = {}, onLogout = {})
    }
}

