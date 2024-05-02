package vn.hoanguyen.compose.onlinestore.features.main.cart

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.CartProduct
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.data_providers.ProductService
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class CartViewmodel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {
    private val _listProduct = MutableStateFlow<List<CartProduct>>(emptyList())
    val listProduct = _listProduct.asStateFlow()

    private val _subTotal = MutableStateFlow<Double>(0.0)
    val subTotal = _subTotal.asStateFlow()

    private val _total = MutableStateFlow<Double>(0.0)
    val total = _total.asStateFlow()

    val feeVAT = 10
    val feeShipping = 80

    init {
        combine(
            _subTotal,
            flowOf(feeVAT), // Convert feeVAT to a flow
            flowOf(feeShipping) // Convert feeShipping to a flow
        ) { subTotal, vat, shipping ->
            subTotal + vat + shipping
        }.onEach {
            _total.value = it
        }.launchIn(viewModelScope)
    }

    fun loadCartProductList() {
        viewModelScope.launch {
            val fakeEmptyCart = Random.nextBoolean()
            _listProduct.emit(if (fakeEmptyCart) emptyList()
            else productService.getProductList(listCategory = emptyList()).reversed().take(4).map {
                CartProduct(
                    product = it,
                    size = if (it.name.length % 2 == 0) "L" else "M",
                    number = 1
                )
            })
            updateSubTotal()
        }
    }

    fun removeProduct(product: Product, size: String) {
        viewModelScope.launch {
            val currentCart = _listProduct.value
            val updatedCart = currentCart.filterNot { it.product == product && it.size == size }
            _listProduct.emit(updatedCart)
            updateSubTotal()
        }
    }

    fun updateProductQuantity(product: Product, size: String, newQuantity: Int) {
        viewModelScope.launch {
            val currentCart = _listProduct.value
            val updatedCart = currentCart.map {
                if (it.product == product && it.size == size) {
                    it.copy(number = newQuantity)
                } else {
                    it
                }
            }
            _listProduct.value = updatedCart
            updateSubTotal()
        }
    }

    private fun updateSubTotal() {
        var newSubTotal = 0.0
        _listProduct.value.forEach { cartItem ->
            newSubTotal += cartItem.number * cartItem.product.price

        }
        _subTotal.value = newSubTotal
    }
}