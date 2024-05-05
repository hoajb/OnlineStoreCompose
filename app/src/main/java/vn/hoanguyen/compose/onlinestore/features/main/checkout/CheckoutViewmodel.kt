package vn.hoanguyen.compose.onlinestore.features.main.checkout

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.CreditCard
import androidx.compose.material.icons.outlined.Money
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.Address
import vn.hoanguyen.compose.onlinestore.data_providers.CardInfo
import vn.hoanguyen.compose.onlinestore.data_providers.UserService
import vn.hoanguyen.compose.onlinestore.features.main.checkout.components.PaymentItem
import javax.inject.Inject

@HiltViewModel
class CheckoutViewmodel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    private val _defaultAddress = MutableStateFlow<Address?>(null)
    val defaultAddress = _defaultAddress.asStateFlow()

    val listPayments = listOf(
        PaymentItem(id = 1, text = "Card", icon = Icons.Outlined.CreditCard),
        PaymentItem(id = 2, text = "Cash", icon = Icons.Outlined.Money),
        PaymentItem(id = 3, text = "Apple Pay", icon = null),
    )

    fun loadDefaultAddress() {
        viewModelScope.launch {
            _defaultAddress.emit(userService.getAddressList()[0])
        }
    }

    private val _cardInfo = MutableStateFlow<CardInfo?>(null)
    val cardList = _cardInfo.asStateFlow()

    fun loadDefaultCard() {
        viewModelScope.launch {
            _cardInfo.emit(userService.getCardList()[0])
        }
    }

}