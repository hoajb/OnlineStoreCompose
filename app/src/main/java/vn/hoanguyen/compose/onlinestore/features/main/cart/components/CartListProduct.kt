package vn.hoanguyen.compose.onlinestore.features.main.cart.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Delete
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.key
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.cart.CartProductListEmptyItem
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorRed
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.formatAsCurrency

@Composable
fun CartListProduct(
    modifier: Modifier = Modifier,
    listProduct: List<CartProduct>,
    subtotal: String,
    feeVAT: String,
    shippingFee: String,
    total: String,
    emptyItem: @Composable () -> Unit = {},
    onUpdateProductQuantity: (Product, String, Int) -> Unit,
    onDeletePressed: (CartProduct) -> Unit,
    onNavigateProductDetails: (String) -> Unit
) {
    if (listProduct.isEmpty()) {
        Box(modifier = Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
            emptyItem()
        }
    } else {
        LazyColumn(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(listProduct) { product ->
                key(product.product.id) {
                    CartItem(
                        product = product,
                        onDeletePressed = onDeletePressed,
                        onUpdateProductQuantity = onUpdateProductQuantity,
                        onNavigateProductDetails = onNavigateProductDetails
                    )
                }
            }

            item {
                TotalItem(
                    subtotal = subtotal,
                    feeVAT = feeVAT,
                    shippingFee = shippingFee,
                    total = total,
                )
            }
        }
    }
}

@Composable
fun TotalItem(
    subtotal: String,
    feeVAT: String,
    shippingFee: String,
    total: String,
) {
    Column(Modifier.fillMaxWidth()) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Sub-total", style = AppTypography.bodyMedium.copy(color = Color.Gray))
            Text(text = subtotal, style = AppTypography.titleSmall)
        }
        Spacer(modifier = Modifier.height(10.dp))

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "VAT (%)", style = AppTypography.bodyMedium.copy(color = Color.Gray))
            Text(text = feeVAT, style = AppTypography.titleSmall)
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Shipping fee", style = AppTypography.bodyMedium.copy(color = Color.Gray))
            Text(text = shippingFee, style = AppTypography.titleSmall)
        }
        Spacer(modifier = Modifier.height(10.dp))
        AppDivider()
        Spacer(modifier = Modifier.height(10.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Total", style = AppTypography.bodyMedium.copy(color = Color.Gray))
            Text(text = total, style = AppTypography.titleSmall)
        }
        Spacer(modifier = Modifier.height(10.dp))
    }
}

@Composable
fun CartItem(
    product: CartProduct,
    onUpdateProductQuantity: (Product, String, Int) -> Unit,
    onDeletePressed: (CartProduct) -> Unit,
    onNavigateProductDetails: (String) -> Unit
) {
    Row(
        Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(16.dp)
            .clickable {
                onNavigateProductDetails.invoke(product.product.id)
            }
    ) {

        AsyncImage(
            model = product.product.imageUrl,
            contentScale = ContentScale.Crop,
            contentDescription = null,
            modifier = Modifier
                .height(100.dp)
                .aspectRatio(1f)
                .clip(shape = RoundedCornerShape(6.dp))
                .background(color = Color.Gray)
        )
        Spacer(modifier = Modifier.width(10.dp))
        Column {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = product.product.name,
                    style = AppTypography.titleMedium,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Icon(
                    modifier = Modifier.clickable {
                        onDeletePressed(product)
                    },
                    imageVector =
                    Icons.Outlined.Delete,
                    tint = ColorRed,
                    contentDescription = "Favorite"
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Size ${product.size}",
                style = AppTypography.bodyMedium.copy(
                    color = Color.Gray
                )
            )
            Spacer(modifier = Modifier.height(8.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    modifier = Modifier.weight(1f),
                    text = product.product.price.formatAsCurrency(),
                    style = AppTypography.titleSmall,
                )
                AddItemCart { newQuantity ->
                    onUpdateProductQuantity(product.product, product.size, newQuantity)
                }
            }
        }
    }
}


@Preview
@Composable
private fun CartListProductPrev() {
    OnlineStoreComposeTheme {
        CartListProduct(
            listProduct =
            (0..2).map {
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
                    ),
                    size = "L",
                    quantity = 1
                )
            },
            subtotal = "$5,870",
            feeVAT = "$0",
            shippingFee = "$80",
            total = "$5,950",
            onUpdateProductQuantity = { _, _, _ -> },
            onNavigateProductDetails = {},
            onDeletePressed = {}
        )
    }
}

@Preview
@Composable
private fun CartListProductEmptyPrev() {
    OnlineStoreComposeTheme {
        CartListProduct(
            listProduct = emptyList(),
            emptyItem = { CartProductListEmptyItem() },
            subtotal = "$5,870",
            feeVAT = "$0",
            shippingFee = "$80",
            total = "$5,950",
            onUpdateProductQuantity = { _, _, _ -> },
            onNavigateProductDetails = {},
            onDeletePressed = {}
        )
    }
}


