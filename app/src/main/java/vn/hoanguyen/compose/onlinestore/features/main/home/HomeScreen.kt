package vn.hoanguyen.compose.onlinestore.features.main.home

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Search
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.components.ListEmptyItem
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterChoice
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterItem
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterPopupContent
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterSelectionBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.FilterSelectionBarViewModel
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeAppBar
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeListProduct
import vn.hoanguyen.compose.onlinestore.features.main.home.components.HomeSearchBar

@Composable
fun HomeScreen(
    viewmodel: HomeViewmodel = hiltViewModel()
) {
    val listFilter = viewmodel.listOfFilter.collectAsState()
    val listProduct = viewmodel.listProduct.collectAsState()
    val context = LocalContext.current

    LaunchedEffect(Unit) {
        viewmodel.loadProductFilter()
        viewmodel.loadProductList()
    }

    HomeBody(
        listFilter = listFilter.value,
        listProduct = listProduct.value,
        onSelectedFilterCategoryChange = { listCategory ->
            viewmodel.loadProductList(listCategory)
        }
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun HomeBody(
    listFilter: List<String>,
    listProduct: List<Product>,
    onSelectedFilterCategoryChange: (List<String>) -> Unit
) {
    val configuration = LocalConfiguration.current
    val sheetState = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    val viewModelFilterSelectionBar: FilterSelectionBarViewModel = viewModel()

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
                onNavigateNotification = {
                    //TODO
                })

            HomeSearchBar(onSearch = {}, onFilter = {
                showBottomSheet = true
            })


            FilterSelectionBar(
                modifier = Modifier.padding(horizontal = 16.dp),
                viewmodel = viewModelFilterSelectionBar,
                listItem = listFilter.mapIndexed { index, item ->
                    FilterItem(id = index, text = item)
                },
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
                    .padding(16.dp),
                listProduct = listProduct,
                emptyItem = { HomeProductListEmptyItem() },
                onFavoritePressed = {}
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
        icon = Icons.Outlined.List,
        title = "No Product Items!",
        content = "Some thing went wrong.\nTry again later.",
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
                category = "TShirt"
            )
        },
        onSelectedFilterCategoryChange = {}

    )
}