@file:OptIn(ExperimentalMaterial3Api::class)

package vn.hoanguyen.compose.onlinestore.features.manament.order

import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.OrderProduct
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorYellow
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.formatAsCurrency

@Composable
fun OrderCompleted(
    listOrders: List<OrderProduct>,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val sheetState = rememberModalBottomSheetState(skipPartiallyExpanded = true)
    Box(
        modifier = Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(listOrders) { order ->
                key(order.cartProduct.product.id) {
                    OrderCompleted(
                        order = order,
                        onNavigateTrack = {
                            showBottomSheet = true
                        },
                    )
                }
            }
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = {
                showBottomSheet = false
            },
            containerColor = Color.White,
            sheetState = sheetState
        ) {
            // Sheet content
            ReviewPopupContent(
                onHidePopup = {
                    scope.launch { sheetState.hide() }.invokeOnCompletion {
                        if (!sheetState.isVisible) {
                            showBottomSheet = false
                        }
                    }
                }
            )
        }
    }
}


@Composable
fun OrderCompleted(
    order: OrderProduct,
    onNavigateTrack: (OrderProduct) -> Unit
) {
    val cart = order.cartProduct
    val product = cart.product
    Row(
        Modifier
            .fillMaxWidth()
            .border(width = 0.5.dp, color = Color.LightGray, shape = RoundedCornerShape(12.dp))
            .background(color = Color.White)
            .padding(16.dp)
    ) {

        AsyncImage(
            model = product.imageUrl,
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
                    text = product.name,
                    style = AppTypography.titleSmall,
                    maxLines = 2,
                    overflow = TextOverflow.Ellipsis
                )
                Text(
                    modifier = Modifier
                        .background(
                            color = Color(0xff43922b).copy(alpha = 0.1f),
                            shape = RoundedCornerShape(6.dp)
                        )
                        .padding(horizontal = 6.dp, vertical = 2.dp),
                    text = "Completed",
                    style = AppTypography.bodySmall.copy(
                        color = Color(0xff43922b)
                    )
                )
            }

            Spacer(modifier = Modifier.height(8.dp))
            Text(
                "Size ${cart.size}",
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
                    text = product.price.formatAsCurrency(),
                    style = AppTypography.titleSmall,
                )

                if (order.status.isNotEmpty()) {
                    Row(
                        modifier = Modifier
                            .border(
                                width = 0.5.dp,
                                color = Color.LightGray,
                                shape = RoundedCornerShape(8.dp)
                            )
                            .padding(vertical = 6.dp, horizontal = 12.dp),
                        verticalAlignment = Alignment.CenterVertically

                    ) {
                        Icon(
                            modifier = Modifier.size(18.dp),
                            imageVector = Icons.Filled.Star,
                            contentDescription = "start",
                            tint = ColorYellow
                        )
                        Spacer(modifier = Modifier.width(4.dp))
                        Text(
                            text = "${order.status}/5",
                            style = AppTypography.bodySmall,
                            textDecoration = TextDecoration.Underline,
                            fontWeight = FontWeight.W600
                        )
                    }
                } else {
                    Button(
                        modifier = Modifier
                            .height(32.dp),
                        colors = ButtonDefaults.buttonColors(containerColor = Color.Black),
                        shape = RoundedCornerShape(6.dp),
                        content = { Text("Leave Review", style = AppTypography.bodySmall) },
                        onClick = { onNavigateTrack.invoke(order) }
                    )
                }
            }
        }
    }
}

@Preview
@Composable
private fun MyOrdersScreenPrev() {
    OnlineStoreComposeTheme {
        OrderCompleted(
            listOrders =
            (0..2).map {
                OrderProduct(
                    cartProduct = CartProduct(
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
                    ),
                    status = if (it == 1) "3.5" else ""
                )
            },
        )

    }
}