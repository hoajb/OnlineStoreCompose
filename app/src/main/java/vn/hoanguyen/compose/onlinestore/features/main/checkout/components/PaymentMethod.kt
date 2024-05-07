package vn.hoanguyen.compose.onlinestore.features.main.checkout.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.ModeEdit
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import vn.hoanguyen.compose.onlinestore.data_providers.CardInfo
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.toCardIcon

@Composable
fun PaymentMethod(
    payment: CardInfo,
    listPayments: List<PaymentItem> = emptyList(),
    onChangeCardClick: () -> Unit
) {
    Column(
        modifier = Modifier.fillMaxWidth(),
    ) {
        Text("Payment Method", style = AppTypography.titleSmall)

        PaymentSelectionBar(
            viewmodel = viewModel(),
            listItem = listPayments,
            onSelectedChange = {
                //nothing
            }
        )

        Spacer(modifier = Modifier.height(8.dp))

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .border(
                    width = 0.5.dp,
                    color = Color.LightGray,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(horizontal = 20.dp, vertical = 4.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                modifier = Modifier.size(40.dp),
                painter = painterResource(id = payment.name.toCardIcon()),
                contentDescription = "card type"
            )

            Spacer(modifier = Modifier.width(8.dp))

            Text(
                "**** **** **** ",
                style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )

            Text(
                modifier = Modifier.weight(1f),
                text = payment.number.substring(payment.number.length - 4, payment.number.length),
                style = AppTypography.bodyMedium.copy(fontWeight = FontWeight.W500)
            )

            IconButton(onClick = onChangeCardClick) {
                Icon(imageVector = Icons.Outlined.ModeEdit, contentDescription = "edit")
            }
        }
    }
}


@Preview
@Composable
private fun DeliveryAddressPrev() {
    OnlineStoreComposeTheme {
        PaymentMethod(
            payment = CardInfo(
                name = "Visa",
                number = "4222222222222"
            ),
            listPayments = listOf(
                PaymentItem(id = 1, text = "Card", icon = Icons.Outlined.CreditCard),
                PaymentItem(id = 1, text = "Cash", icon = Icons.Outlined.Money),
                PaymentItem(id = 1, text = "Apple Pay", icon = null),
            ),
            onChangeCardClick = {}
        )
    }
}