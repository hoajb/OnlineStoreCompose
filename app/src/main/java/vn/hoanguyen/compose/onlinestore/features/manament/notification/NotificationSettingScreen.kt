package vn.hoanguyen.compose.onlinestore.features.manament.notification

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Checkbox
import androidx.compose.material3.CheckboxDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import kotlin.random.Random

private val listSetting = listOf(
    "General Notifications",
    "Sound",
    "Vibrate",
    "Special Offers",
    "Promo & Discounts",
    "Payments",
    "Cashback",
    "App Updates",
    "New Service Available",
    "New Tips Available",
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NotificationSettingScreen(
    onBack: () -> Unit,
) {
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "Notifications", onBack = onBack)
    }) { padding ->
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState())
                .background(Color.White)
                .padding(16.dp)
        ) {
            listSetting.map { title ->
                var checked by remember {
                    mutableStateOf(Random.nextBoolean())
                }
                NotificationRow(title = title,
                    checked = checked,
                    onCheckedChange = { checked = it })
            }
        }
    }
}

@Composable
fun NotificationRow(
    modifier: Modifier = Modifier,
    title: String,
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit
) {
    Row(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween
    ) {
        Text(text = title, style = AppTypography.bodyMedium)
        Checkbox(
            checked = checked, onCheckedChange = onCheckedChange, colors = CheckboxDefaults.colors(
                checkedColor = Color.Black
            )
        )
    }
    AppDivider()
}

@Preview
@Composable
private fun PaymentManagementScreenPrev() {
    OnlineStoreComposeTheme {
        NotificationSettingScreen(onBack = {})
    }
}