package vn.hoanguyen.compose.onlinestore.features.manament.order

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.OrderProduct
import vn.hoanguyen.compose.onlinestore.data_providers.ProductService
import javax.inject.Inject
import kotlin.random.Random

enum class OrderStatus(val text: String) {
    InTransit("In Transit"),
    Picked("Picked"),
    Picking("Picking"),
}

@HiltViewModel
class OrderViewmodel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {
    private val _listOngoingOrders = MutableStateFlow<List<OrderProduct>>(emptyList())
    val listOngoingOrders = _listOngoingOrders.asStateFlow()

    private val _listCompletedOrders = MutableStateFlow<List<OrderProduct>>(emptyList())
    val listCompletedOrders = _listCompletedOrders.asStateFlow()

    fun loadOngoingProductList() {
        viewModelScope.launch {
            _listOngoingOrders.emit(
                productService.getProductList(listCategory = emptyList()).reversed().take(6).map {
                    OrderProduct(
                        cartProduct = CartProduct(
                            product = it,
                            size = if (it.name.length % 2 == 0) "L" else "M",
                            quantity = 1
                        ),
                        status = getRandomOrderStatus().text
                    )

                })
        }
    }

    fun loadCompletedProductList() {
        viewModelScope.launch {
            _listCompletedOrders.emit(
                productService.getProductList(listCategory = emptyList()).take(5).map {
                    OrderProduct(
                        cartProduct = CartProduct(
                            product = it,
                            size = if (it.name.length % 2 == 0) "L" else "M",
                            quantity = 1
                        ),
                        status = if (it.name.length % 3 == 0) "4.5" else ""
                    )

                })
        }
    }

    private fun getRandomOrderStatus(): OrderStatus {
        val statuses = OrderStatus.entries.toTypedArray()
        return statuses[Random.nextInt(statuses.size)]
    }
}