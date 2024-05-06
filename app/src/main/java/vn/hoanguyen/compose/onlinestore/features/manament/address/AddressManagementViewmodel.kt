package vn.hoanguyen.compose.onlinestore.features.manament.address

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Address
import vn.hoanguyen.compose.onlinestore.data_providers.UserService
import javax.inject.Inject

@HiltViewModel
class AddressManagementViewmodel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    private val _addressList = MutableStateFlow<List<Address>>(emptyList())
    val addressList = _addressList.asStateFlow()

    fun loadDefaultAddress() {
        viewModelScope.launch {
            _addressList.emit(userService.getAddressList())
        }
    }
}