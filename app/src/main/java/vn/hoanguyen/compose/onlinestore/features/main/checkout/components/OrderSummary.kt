package vn.hoanguyen.compose.onlinestore.features.main.checkout.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.features.main.cart.components.TotalItem
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun OrderSummary(
    subtotal: String,
    feeVAT: String,
    shippingFee: String,
    total: String,
) {
    Column {
        Text("Order Summary", style = AppTypography.titleSmall)
        Spacer(modifier = Modifier.height(16.dp))
        TotalItem(
            subtotal = subtotal,
            feeVAT = feeVAT,
            shippingFee = shippingFee,
            total = total,
        )
    }
}

@Preview
@Composable
private fun OrderSummaryPrev() {
    OnlineStoreComposeTheme {
        OrderSummary(
            subtotal = "$5,870",
            feeVAT = "$0.00",
            shippingFee = "$80",
            total = "$5,950",
        )
    }
}