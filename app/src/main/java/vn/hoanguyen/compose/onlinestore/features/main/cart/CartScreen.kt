package vn.hoanguyen.compose.onlinestore.features.main.cart

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ArrowForward
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.cart.components.CartListProduct
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeAppBar
import vn.hoanguyen.compose.onlinestore.utils.formatAsCurrency

@Composable
fun CartScreen(
    viewmodel: CartViewmodel = hiltViewModel(),
    onNavigateToCheckout: () -> Unit,
    onNavigateProductDetails: (String) -> Unit,
    onNavigateNotifications: () -> Unit,
) {
    val listProduct = viewmodel.listProduct.collectAsState()
    val subtotal = viewmodel.subTotal.collectAsState()
    val total = viewmodel.total.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.loadCartProductList()
    }

    CartBody(
        listProduct = listProduct.value,
        subtotal = subtotal.value.formatAsCurrency(),
        feeVAT = viewmodel.feeVAT.formatAsCurrency(),
        shippingFee = viewmodel.feeShipping.formatAsCurrency(),
        total = total.value.formatAsCurrency(),
        onDeleteProduct = { product, size -> viewmodel.removeProduct(product, size) },
        onUpdateProductQuantity = { product, size, newQuantity ->
            viewmodel.updateProductQuantity(
                product, size, newQuantity
            )
        },
        onNavigateToCheckout = onNavigateToCheckout,
        onNavigateProductDetails = onNavigateProductDetails,
        onNavigateNotifications = onNavigateNotifications
    )

}

@Composable
private fun CartBody(
    listProduct: List<CartProduct>,
    subtotal: String,
    feeVAT: String,
    shippingFee: String,
    total: String,
    onUpdateProductQuantity: (Product, String, Int) -> Unit,
    onDeleteProduct: (Product, String) -> Unit,
    onNavigateToCheckout: () -> Unit,
    onNavigateProductDetails: (String) -> Unit,
    onNavigateNotifications: () -> Unit,
) {
    Scaffold(Modifier.background(Color.White),
        topBar = {
            HomeAppBar(title = "My Cart", onNavigateNotification = onNavigateNotifications)
        }) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .background(color = Color.White)
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            CartListProduct(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                listProduct = listProduct.toList(),
                subtotal = subtotal,
                feeVAT = feeVAT,
                shippingFee = shippingFee,
                total = total,
                emptyItem = {
                    CartProductListEmptyItem()
                },
                onDeletePressed = {
                    onDeleteProduct(it.product, it.size)
                },
                onUpdateProductQuantity = onUpdateProductQuantity,
                onNavigateProductDetails = onNavigateProductDetails
            )

            if (listProduct.isNotEmpty()) Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
                    .defaultMinSize(minHeight = 50.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                onClick = onNavigateToCheckout
            ) {
                Row {
                    Text(text = "Go To Checkout")
                    Spacer(modifier = Modifier.width(8.dp))
                    Icon(
                        imageVector = Icons.Outlined.ArrowForward, contentDescription = "checkout"
                    )
                }
            }
        }
    }
}

@Composable
fun CartProductListEmptyItem() {
    ListEmptyItem(
        icon = Icons.Outlined.ShoppingCart,
        title = "Your Cart Is Empty!",
        content = "When you add products,\nThey'll appear here.",
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    CartBody(listProduct = (0..2).map {
        CartProduct(
            product = Product(
                name = "Regular Fit Black No.$it ${if (it % 2 == 1) "ABC ABC 2nd Lines" else ""}",
                discount = 10,
                price = 800.0 + 100 * it,
                id = it.toString(),
                description = "No",
                imageUrl = "https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125",
                category = "TShirt",
                sizeList = listOf("S", "M", "L")
            ), size = "L", quantity = 1
        )
    },
        subtotal = "$5,870",
        feeVAT = "$0",
        shippingFee = "$80",
        total = "$5,950",
        onUpdateProductQuantity = { _, _, _ -> },
        onDeleteProduct = { _, _ -> },
        onNavigateToCheckout = {},
        onNavigateNotifications = {},
        onNavigateProductDetails = {})

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenEmptyPrev() {
    CartBody(listProduct = listOf(),
        subtotal = "$5,870",
        feeVAT = "$0",
        shippingFee = "$80",
        total = "$5,950",
        onUpdateProductQuantity = { _, _, _ -> },
        onDeleteProduct = { _, _ -> },
        onNavigateToCheckout = {},
        onNavigateNotifications = {},
        onNavigateProductDetails = {})
}