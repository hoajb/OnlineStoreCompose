package vn.hoanguyen.compose.onlinestore.features.manament.payment

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.CardInfo
import vn.hoanguyen.compose.onlinestore.data_providers.UserService
import javax.inject.Inject

@HiltViewModel
class PaymentManagementViewmodel @Inject constructor(
    private val userService: UserService
) : ViewModel() {

    private val _cardList = MutableStateFlow<List<CardInfo>>(emptyList())
    val cardList = _cardList.asStateFlow()

    fun loadDefaultPayment() {
        viewModelScope.launch {
            _cardList.emit(userService.getCardList())
        }
    }
}