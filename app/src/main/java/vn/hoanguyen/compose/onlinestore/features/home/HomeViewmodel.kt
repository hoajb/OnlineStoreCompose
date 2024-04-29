package vn.hoanguyen.compose.onlinestore.features.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.data_providers.ProductService
import javax.inject.Inject

@HiltViewModel
class HomeViewmodel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {
    private val _listOfFilter = MutableStateFlow<List<String>>(emptyList())
    val listOfFilter = _listOfFilter.asStateFlow()

    private val _listProduct = MutableStateFlow<List<Product>>(emptyList())
    val listProduct = _listProduct.asStateFlow()

    fun loadProductFilter() {
        viewModelScope.launch {
            _listOfFilter.emit(productService.getProductFilter())
        }
    }

    fun loadProductList() {
        viewModelScope.launch {
            _listProduct.emit(productService.getProductList())
        }
    }

}