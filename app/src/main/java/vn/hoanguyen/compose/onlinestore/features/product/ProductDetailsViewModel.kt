package vn.hoanguyen.compose.onlinestore.features.product

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.data_providers.ProductService
import javax.inject.Inject

sealed class ProductState {
    data class Success(val product: Product) : ProductState()
    data object Loading : ProductState()
    data object Error : ProductState()
}

@HiltViewModel
class ProductDetailsViewModel @Inject constructor(
    private val productService: ProductService,
    private val savedStateHandle: SavedStateHandle
) : ViewModel() {

    //still can get id from here
//    private val productId: String =
//        savedStateHandle.get<String>(NavRoute.ProductDetails.productId).orEmpty()
    val _productState: MutableStateFlow<ProductState> = MutableStateFlow(ProductState.Loading)
    val productState = _productState.asStateFlow()

    fun loadProduct(productId: String) {
        viewModelScope.launch {
            _productState.emit(ProductState.Loading)
            delay(500) // simulate API call
            productService.getProductById(productId)?.let {
                _productState.emit(
                    ProductState.Success(it)
                )
            }
        }
    }
}