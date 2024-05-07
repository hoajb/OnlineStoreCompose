@file:OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)

package vn.hoanguyen.compose.onlinestore.features.manament.order

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.components.AppTopAppBar
import vn.hoanguyen.compose.onlinestore.components.tabs.AppCustomTab
import vn.hoanguyen.compose.onlinestore.ui.theme.OnlineStoreComposeTheme

@Composable
fun MyOrdersScreen(
    viewmodel: OrderViewmodel = hiltViewModel(),
    onBack: () -> Unit,
) {
    LaunchedEffect(key1 = Unit) {
        viewmodel.loadOngoingProductList()
        viewmodel.loadCompletedProductList()
    }
    Scaffold(Modifier.background(Color.White), topBar = {
        AppTopAppBar(title = "My Orders", onBack = onBack)
    }) { padding ->
        val listOngoingOrders by viewmodel.listOngoingOrders.collectAsState()
        val listCompletedOrders by viewmodel.listCompletedOrders.collectAsState()
        Column(
            Modifier
                .padding(padding)
                .fillMaxWidth()
                .background(Color.White)
                .padding(16.dp)
        ) {
            val pagerState = rememberPagerState(pageCount = { 2 })
            val scope = rememberCoroutineScope()
            TopBarTab(
                selectedItemIndex = pagerState.currentPage,
                onSelected = { index ->
                    scope.launch { pagerState.animateScrollToPage(index) }
                }
            )
            Spacer(modifier = Modifier.width(8.dp))
            HorizontalPager(state = pagerState) { page ->
                // Our page content
                when (page) {
                    0 -> OrderOngoing(listOrders = listOngoingOrders,
                        onNavigateToTrackOrder = {
                            //TODO
                        })

                    1 -> OrderCompleted(
                        listOrders = listCompletedOrders,
                        onNavigateToTrackOrder = {
                            //TODO
                        }
                    )
                }
            }
        }
    }
}

@Composable
fun TopBarTab(
    selectedItemIndex: Int = 0,
    onSelected: (Int) -> Unit
) {
    val configuration = LocalConfiguration.current

    AppCustomTab(
        items = listOf("Ongoing", "Completed"),
        selectedItemIndex = selectedItemIndex,
        onClick = onSelected,
        tabWidth = configuration.screenWidthDp.dp - 32.dp
    )
}

@Preview
@Composable
private fun MyOrdersScreenPrev() {
    OnlineStoreComposeTheme {
        MyOrdersScreen(
            onBack = {}
        )
    }
}