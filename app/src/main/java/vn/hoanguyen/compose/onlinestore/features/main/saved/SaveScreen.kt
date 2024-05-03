package vn.hoanguyen.compose.onlinestore.features.main.saved

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeAppBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeListProduct

@Composable
fun SavedScreen(
    viewmodel: SavedViewmodel = hiltViewModel(),
    onNavigateProductDetails: (String) -> Unit
) {
    val listProduct = viewmodel.listProduct.collectAsState()

    LaunchedEffect(Unit) {
        viewmodel.loadFavoriteProductList()
    }

    SavedBody(
        listProduct = listProduct.value,
        onNavigateProductDetails = onNavigateProductDetails
    )
}

@Composable
private fun SavedBody(
    listProduct: List<Product>,
    onNavigateProductDetails: (String) -> Unit
) {
    // remove item only in UI
    val listState = remember { mutableStateListOf(*listProduct.toTypedArray()) }
    Scaffold(
        Modifier.background(Color.White),
        topBar = {
            HomeAppBar(
                title = "Saved Items",
                onNavigateNotification = {}
            )
        }
    ) { padding ->
        Column(
            modifier = Modifier
                .padding(padding)
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            HomeListProduct(
                modifier = Modifier
                    .weight(1f)
                    .padding(16.dp),
                listProduct = listState.toList(),
                defaultFavorite = true,
                emptyItem = {
                    SavedProductListEmptyItem()
                },
                onFavoritePressed = {
                    listState.remove(it)
                },
                onItemPressed = onNavigateProductDetails
            )
        }
    }
}

@Composable
fun SavedProductListEmptyItem() {
    ListEmptyItem(
        icon = Icons.Outlined.FavoriteBorder,
        title = "No Saved Items!",
        content = "You don't have any saved items.\nGo to home and add some.",
    )
}

@Preview(showBackground = true)
@Composable
private fun HomeScreenPrev() {
    SavedBody(
        listProduct =
        (0..10).map {
            Product(
                name = "Regular Fit Black No.$it",
                discount = 10,
                price = 800.0 + 100 * it,
                id = it.toString(),
                description = "No",
                imageUrl = "https://static.pullandbear.net/2/photos//2024/V/0/2/p/3241/570/711/3241570711_2_1_8.jpg?t=1713773719598&imwidth=1125",
                category = "TShirt",
                sizeList = listOf("S","M","L")
            )
        },
        onNavigateProductDetails = {},
    )

}

@Preview(showBackground = true)
@Composable
private fun HomeScreenEmptyPrev() {
    SavedBody(
        listProduct = listOf(),
        onNavigateProductDetails = {}
    )
}