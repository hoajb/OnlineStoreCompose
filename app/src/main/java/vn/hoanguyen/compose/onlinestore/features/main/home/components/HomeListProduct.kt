package vn.hoanguyen.compose.onlinestore.features.main.home.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Favorite
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.key
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.saved.SavedProductListEmptyItem
import vn.hoanguyen.compose.onlinestore.ui.theme.AppTypography
import vn.hoanguyen.compose.onlinestore.ui.theme.ColorRed
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme
import vn.hoanguyen.compose.onlinestore.utils.formatAsCurrency

@Composable
fun HomeListProduct(
    modifier: Modifier = Modifier,
    listProduct: List<Product>,
    defaultFavorite: Boolean = false, // useful for Saved Items Page
    emptyItem: @Composable () -> Unit = {},
    onFavoritePressed: (Product) -> Unit
) {
    if (listProduct.isEmpty()) {
        Box(
            modifier = Modifier
                .fillMaxSize()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            emptyItem()
        }
    } else {
        LazyVerticalGrid(
            modifier = modifier,
            columns = GridCells.Fixed(2),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp),
        ) {
            items(listProduct) { product ->
                key(product.id) {
                    ProductItem(
                        product = product,
                        defaultFavorite = defaultFavorite,
                        onFavoritePressed = onFavoritePressed
                    )
                }
            }
        }
    }
}

@Composable
fun ProductItem(
    product: Product,
    defaultFavorite: Boolean = false,
    onFavoritePressed: (Product) -> Unit
) {
    var isFavoriteState by remember {
        mutableStateOf(defaultFavorite)
    }
    Column {
        Box(
            Modifier
                .aspectRatio(1f)
                .fillMaxWidth()
                .clip(shape = RoundedCornerShape(12.dp))
                .background(color = Color.LightGray)
        ) {
            AsyncImage(
                model = product.imageUrl,
                contentScale = ContentScale.Crop,
                contentDescription = null,
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
            )

            Box(
                modifier = Modifier
                    .padding(8.dp)
                    .background(color = Color.White, shape = RoundedCornerShape(6.dp))
                    .clip(RoundedCornerShape(6.dp))
                    .align(Alignment.TopEnd)
                    .clickable {
                        if (defaultFavorite.not()) {
                            isFavoriteState = isFavoriteState.not()
                        }
                        onFavoritePressed(product)
                    }
                    .padding(4.dp),
            ) {
                Icon(
                    imageVector = if (isFavoriteState)
                        Icons.Outlined.Favorite else Icons.Outlined.FavoriteBorder,
                    tint = if (isFavoriteState) ColorRed else Color.Black,
                    contentDescription = "Favorite"
                )
            }
        }
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            product.name,
            style = AppTypography.titleMedium,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Spacer(modifier = Modifier.height(4.dp))
        Text(
            product.price.formatAsCurrency(),
            style = AppTypography.bodyMedium.copy(
                color = Color.Gray
            )
        )
    }
}

@Preview
@Composable
private fun HomeListProductPrev() {
    OnlineStoreComposeTheme {
        HomeListProduct(listProduct =
        (0..10).map {
            Product(
                name = "Regular Fit Black No.$it",
                discount = 10,
                price = 800.0 + 100 * it,
                id = it.toString(),
                description = "No",
                imageUrl = "https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125",
                category = "TShirt"
            )
        }
        ) {}
    }
}

@Preview
@Composable
private fun HomeListProductEmptyPrev() {
    OnlineStoreComposeTheme {
        HomeListProduct(
            listProduct = emptyList(),
            emptyItem = { SavedProductListEmptyItem() }
        ) {}
    }
}


