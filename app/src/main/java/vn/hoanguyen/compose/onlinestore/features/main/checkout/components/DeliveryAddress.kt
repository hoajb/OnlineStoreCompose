package vn.hoanguyen.compose.onlinestore.features.main.checkout.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.LocationOn
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import vn.hoanguyen.compose.onlinestore.data_providers.Address
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme


@Composable
fun DeliveryAddress(
    address: Address,
    onChangeAddressClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Absolute.SpaceBetween
        ) {
            Text("Delivery Address", style = AppTypography.titleSmall)
            Text(
                modifier = Modifier.clickable {
                    onChangeAddressClick.invoke()
                },
                text = "Change",
                style = AppTypography.bodyMedium,
                textDecoration = TextDecoration.Underline
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                modifier = Modifier.size(24.dp),
                imageVector = Icons.Outlined.LocationOn,
                contentDescription = "location",
                tint = Color.Gray
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(address.name, style = AppTypography.titleSmall)
        }
        Text(
            modifier = Modifier.padding(start = 32.dp),
            text = address.address,
            style = AppTypography.bodyMedium.copy(
                color = Color.Gray
            )
        )
    }
}


@Preview
@Composable
private fun DeliveryAddressPrev() {
    OnlineStoreComposeTheme {
        DeliveryAddress(
            address = Address(
                name = "Home",
                address = "12 Le Loi Street, Hue City, Vietnam, Postal Code: 530000"
            ),
            onChangeAddressClick = {}
        )
    }
}