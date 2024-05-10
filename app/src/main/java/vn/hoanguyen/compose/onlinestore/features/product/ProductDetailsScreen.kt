@file:OptIn(
    ExperimentalMaterial3Api::class,
)

package vn.hoanguyen.compose.onlinestore.features.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Star
import androidx.compose.material.icons.outlined.ErrorOutline
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.ShoppingBag
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import vn.hoanguyen.compose.onlinestore.components.AppDivider
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.components.Loading
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.product.components.SizeItem
import vn.hoanguyen.compose.onlinestore.features.product.components.SizeSelectionBar
import vn.hoanguyen.compose.onlinestore.features.product.components.SizeSelectionBarViewModel
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorRed
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorYellow
import vn.hoanguyen.compose.onlinestore.utils.formatAsCurrency

@Composable
fun ProductDetailsScreen(
    viewModel: ProductDetailsViewModel = hiltViewModel(),
    productId: String, onBack: () -> Unit,
    onNavigateToCart: (CartProduct) -> Unit
) {
    LaunchedEffect(Unit) {
        viewModel.loadProduct(productId)
    }
    val product = viewModel.productState.collectAsState()
    Scaffold(topBar = { AppTopAppBar(title = "Details", onBack = onBack) }) { padding ->
        Box(
            Modifier
                .padding(padding)
                .background(color = Color.White)) {
            when (product.value) {
                is ProductState.Loading -> Box(
                    Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    Loading()
                }

                is ProductState.Success -> {
                    ProductDetailBody(
                        product = (product.value as ProductState.Success).product,
                        onNavigateToCart = onNavigateToCart
                    )
                }

                is ProductState.Error -> {
                    Box(
                        Modifier.fillMaxSize(),
                        contentAlignment = Alignment.Center
                    ) {
                        ProductErrorItem()
                    }
                }
            }
        }

    }
}

@Composable
fun ProductDetailBody(
    product: Product,
    onNavigateToCart: (CartProduct) -> Unit
) {
    val (isFavoriteState, updateFavorite) = remember {
        mutableStateOf(false)
    }
    val viewmodelSelectionBar = viewModel<SizeSelectionBarViewModel>()
    Column(
        modifier = Modifier
            .fillMaxSize()

    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 16.dp, end = 16.dp)
                .weight(1f)
                .verticalScroll(rememberScrollState())
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(shape = RoundedCornerShape(12.dp))
                    .background(Color.Transparent)
            ) {
                AsyncImage(
                    model = product.imageUrl,
                    contentScale = ContentScale.Crop,
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .wrapContentHeight()
                )

                Box(
                    modifier = Modifier
                        .padding(8.dp)
                        .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                        .clip(RoundedCornerShape(6.dp))
                        .align(Alignment.TopEnd)
                        .clickable {
                            updateFavorite(!isFavoriteState)
                        }
                        .padding(4.dp),
                ) {
                    Icon(
                        imageVector = if (isFavoriteState) Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                        tint = if (isFavoriteState) ColorRed else Color.Black,
                        contentDescription = "Favorite"
                    )
                }
            }
            Padding()
            Text(product.name, style = AppTypography.titleLarge)
            Padding()

            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Filled.Star,
                    contentDescription = "start",
                    tint = ColorYellow
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "4.0/5",
                    style = AppTypography.bodyMedium,
                    textDecoration = TextDecoration.Underline,
                    fontWeight = FontWeight.W600
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "(45 reviews)",
                    style = AppTypography.bodyMedium.copy(color = Color.Gray)
                )
            }
            Padding()
            Text(
                text = product.description,
                style = AppTypography.bodyMedium.copy(color = Color.Gray)
            )
            Padding()
            Text("Choose size", style = AppTypography.titleMedium)

            SizeSelectionBar(
                viewmodel = viewmodelSelectionBar,
                listItem = product.sizeList.mapIndexed { index, item -> SizeItem(index, item) },
                onSelectedChange = {}
            )
        }

        AppDivider()
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Column(
                modifier = Modifier.weight(1f),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Column {
                    Text(
                        text = "Price",
                        style = AppTypography.bodyMedium.copy(color = Color.Gray),
                        fontWeight = FontWeight.W500
                    )
                    Text(text = product.price.formatAsCurrency(), style = AppTypography.titleLarge)
                }
            }
            Spacer(modifier = Modifier.width(16.dp))
            Button(
                modifier = Modifier
                    .fillMaxWidth()
                    .defaultMinSize(minHeight = 50.dp)
                    .weight(2f),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.Black,
                ),
                shape = RoundedCornerShape(12.dp),
                content = {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Outlined.ShoppingBag,
                            contentDescription = "add_to_cart"
                        )
                        Spacer(modifier = Modifier.width(8.dp))
                        Text("Add to Cart")
                    }
                },
                onClick = {
                    onNavigateToCart.invoke(
                        CartProduct(
                            product = product,
                            size = product.sizeList[viewmodelSelectionBar.selectedIndexList.get(0)],
                            quantity = 1
                        )
                    )
                }
            )
        }
    }


}

@Composable
fun Padding() {
    Spacer(modifier = Modifier.height(16.dp))
}

@Composable
fun ProductErrorItem() {
    ListEmptyItem(
        icon = Icons.Outlined.ErrorOutline,
        title = "No Product Found!",
        content = "Some thing went wrong.\nTry again later.",
    )
}

@Preview
@Composable
private fun ProductDetailBodyPrev() {
    Surface {
        ProductDetailBody(
            product = Product(
                name = "Regular Fit Black No.$1",
                discount = 10,
                price = 800.0 + 100 * 1,
                id = 1.toString(),
                description = "The name says it all, the right size slightly snugs the body leaving enough room for comfort in the sleeves and waist.",
                imageUrl = "https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125",
                category = "TShirt",
                sizeList = listOf("S", "M", "L")
            ),
            onNavigateToCart = {}
        )
    }

}