@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.main.checkout

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.data_providers.Address
import vn.hoanguyen.compose.onlinestore.data_providers.CardInfo
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.DeliveryAddress
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.EnterPromoCode
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.OrderSummary
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.PaymentItem
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.PaymentMethod
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun CheckoutScreen(
    viewmodel: CheckoutViewmodel = hiltViewModel(),
    onBack: () -> Unit = {},
    onNavigateToChangeAddress: () -> Unit,
    onNavigateToChangePaymentMethod: () -> Unit,

    ) {
    LaunchedEffect(Unit) {
        viewmodel.loadDefaultAddress()
        viewmodel.loadDefaultCard()
    }
    val defaultAddress = viewmodel.defaultAddress.collectAsState()
    val cardList = viewmodel.cardList.collectAsState()
    val listPayments = viewmodel.listPayments

    CheckoutBody(
        defaultAddress = defaultAddress.value,
        defaultCardInfo = cardList.value,
        listPayments = listPayments,
        onBack = onBack,
        onNavigateToChangeAddress = onNavigateToChangeAddress,
        onNavigateToChangePaymentMethod = onNavigateToChangePaymentMethod,
    )
}

@Composable
fun CheckoutBody(
    defaultAddress: Address?,
    defaultCardInfo: CardInfo?,
    listPayments: List<PaymentItem>,
    onBack: () -> Unit = {},
    onNavigateToChangeAddress: () -> Unit,
    onNavigateToChangePaymentMethod: () -> Unit,
) {
    Scaffold(
        Modifier.background(Color.White),
        topBar = {
            AppTopAppBar(title = "Checkout", onBack = onBack)
        }) { padding ->

        val rememberScrollState = rememberScrollState()
        val scope = rememberCoroutineScope()
        Column(
            Modifier
                .padding(padding)
                .fillMaxSize()
                .background(Color.White)
                .padding(16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .weight(1f)
                    .verticalScroll(rememberScrollState)
            ) {
                defaultAddress?.let {
                    DeliveryAddress(it, onNavigateToChangeAddress)
                }
                Spacer(modifier = Modifier.height(8.dp))
                AppDivider()
                Spacer(modifier = Modifier.height(16.dp))
                defaultCardInfo?.let {
                    PaymentMethod(
                        payment = it,
                        listPayments = listPayments,
                        onChangeCardClick = onNavigateToChangePaymentMethod
                    )
                }

                Spacer(modifier = Modifier.height(16.dp))
                OrderSummary(
                    subtotal = "$5,870",
                    feeVAT = "$0.00",
                    shippingFee = "$80",
                    total = "$5,950",
                )
                Spacer(modifier = Modifier.height(16.dp))
                EnterPromoCode(
                    onAddedPromoCode = {
                        scope.launch { rememberScrollState.animateScrollTo(Int.MAX_VALUE) }
                    }
                )
            }

            Button(modifier = Modifier
                .fillMaxWidth()
                .defaultMinSize(minHeight = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = {
                    //TODO
                }) {
                Text(text = "Place Order")
            }
        }
    }
}

@Preview
@Composable
private fun CheckoutScreenPrev() {
    OnlineStoreComposeTheme {
        CheckoutBody(
            defaultAddress = Address(
                name = "Home",
                address = "12 Le Loi Street, Hue City, Vietnam, Postal Code: 530000"
            ),
            defaultCardInfo = CardInfo(
                name = "Visa",
                number = "4222222222222"
            ),
            listPayments = listOf(
                PaymentItem(id = 1, text = "Card", icon = Icons.Outlined.CreditCard),
                PaymentItem(id = 2, text = "Cash", icon = Icons.Outlined.Money),
                PaymentItem(id = 3, text = "Apple Pay", icon = null),
            ),
            onBack = {},
            onNavigateToChangeAddress = {},
            onNavigateToChangePaymentMethod = {}
        )
    }
}