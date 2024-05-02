package vn.hoanguyen.compose.onlinestore.features.main.checkout

import androidx.compose.runtime.Composable
import vn.hoanguyen.compose.onlinestore.features.EmptyScreen

@Composable
fun CheckoutScreen(onBack: () -> Unit = {}) {
    EmptyScreen("CheckoutScreen", onBack = onBack)
}