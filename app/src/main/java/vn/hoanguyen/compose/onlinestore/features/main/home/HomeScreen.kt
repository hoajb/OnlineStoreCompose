package vn.hoanguyen.compose.onlinestore.features.main.home

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.List
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Surface
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterChoice
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterItem
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterPopupContent
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterSelectionBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeAppBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeListProduct
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewmodel: HomeViewmodel = hiltViewModel(),
    onNavigateProductDetails: (String) -> Unit,
    onNavigateNotifications: () -> Unit,
) {
    val listFilter = viewmodel.listOfFilter.collectAsState()
    val listProduct = viewmodel.listProduct.collectAsState()
    var searchQueryText by remember { mutableStateOf("") }
    val filterCategoryList = remember { mutableStateOf<List<String>>(emptyList()) }

    LaunchedEffect(Unit) {
        viewmodel.loadProductFilter()
        viewmodel.loadProductList()
    }

    HomeBody(
        listFilter = listFilter.value,
        listProduct = listProduct.value,
        onSelectedFilterCategoryChange = { filter ->
            filterCategoryList.value = filter
            viewmodel.loadProductList(listCategory = filter, query = searchQueryText)
        },
        onNavigateProductDetails = onNavigateProductDetails,
        onNavigateNotifications = onNavigateNotifications,
        onSearchProduct = { query ->
            searchQueryText = query
            viewmodel.loadProductList(
                listCategory = filterCategoryList.value,
                query = searchQueryText
            )
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBody(
    listFilter: List<String>,
    listProduct: List<Product>,
    onSelectedFilterCategoryChange: (List<String>) -> Unit,
    onNavigateProductDetails: (String) -> Unit,
    onNavigateNotifications: () -> Unit,
    onSearchProduct: (String) -> Unit,
) {
    val configuration = LocalConfiguration.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }


    Surface(
        color = Color.White
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .verticalScroll(rememberScrollState()),
        ) {
            HomeAppBar(
                title = "Discover",
                onNavigateNotification = onNavigateNotifications
            )

            HomeSearchBar(
                onValueChange = onSearchProduct,
                onSearch = onSearchProduct,
                onFilter = {
                    showBottomSheet = true
                })


            FilterSelectionBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                listItem = listFilter.mapIndexed { index, item ->
                    FilterItem(id = index, text = item)
                },
                defaultSelectedIndex = 0,
                filterChoice = FilterChoice.Multi,
                onSelectedChange = { indexList ->
                    if (indexList.contains(0)) { // All
                        onSelectedFilterCategoryChange.invoke(emptyList())
                    } else {
                        onSelectedFilterCategoryChange.invoke(
                            indexList.map { index -> listFilter[index] })
                    }
                })

            HomeListProduct(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxSize()
                    .padding(16.dp),
                listProduct = listProduct,
                emptyItem = { HomeProductListEmptyItem() },
                onFavoritePressed = {},
                onItemPressed = onNavigateProductDetails
            )
        }
    }

    if (showBottomSheet) {
        ModalBottomSheet(
            modifier = Modifier.defaultMinSize(minHeight = configuration.screenHeightDp.dp / 2),
            onDismissRequest = {
                showBottomSheet = false
            },
            containerColor = Color.White,
            sheetState = sheetState
        ) {
            // Sheet content
            FilterPopupContent(
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
fun HomeProductListEmptyItem() {
    ListEmptyItem(
        icon = Icons.AutoMirrored.Filled.List,
        title = "No Product Items!",
        content = "Update your searching and filtering.\nPlease try again.",
    )
}

@Preview
@Composable
private fun HomeScreenPrev() {
    HomeBody(
        listFilter = listOf("All", "Tshirt"),
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
                sizeList = listOf("S", "M", "L")
            )
        },
        onSelectedFilterCategoryChange = {},
        onNavigateProductDetails = {},
        onNavigateNotifications = {},
        onSearchProduct = {},

        )
}

@Preview
@Composable
private fun HomeScreenEmptyPrev() {
    HomeBody(
        listFilter = listOf("All", "Tshirt"),
        listProduct = listOf(),
        onSelectedFilterCategoryChange = {},
        onNavigateProductDetails = {},
        onNavigateNotifications = {},
        onSearchProduct = {},

        )
}