package vn.hoanguyen.compose.onlinestore.features.main.saved

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Product
import vn.hoanguyen.compose.onlinestore.data_providers.ProductService
import javax.inject.Inject
import kotlin.random.Random

@HiltViewModel
class SavedViewmodel @Inject constructor(
    private val productService: ProductService
) : ViewModel() {
    private val _listProduct = MutableStateFlow<List<Product>>(emptyList())
    val listProduct = _listProduct.asStateFlow()

    fun loadFavoriteProductList() {
        viewModelScope.launch {
            _listProduct.emit(
                productService.getProductList(listCategory = emptyList()).take(6)
            )
        }
    }

}