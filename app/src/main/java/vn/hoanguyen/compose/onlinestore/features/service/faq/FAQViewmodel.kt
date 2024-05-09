package vn.hoanguyen.compose.onlinestore.features.service.faq

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import vn.hoanguyen.compose.onlinestore.data_providers.FAQ
import vn.hoanguyen.compose.onlinestore.data_providers.FAQType
import vn.hoanguyen.compose.onlinestore.data_providers.UserService
import vn.hoanguyen.compose.onlinestore.data_providers.toFAQType
import javax.inject.Inject

@HiltViewModel
class FAQViewmodel @Inject constructor(
    private val userService: UserService
) : ViewModel() {
    private val _faqList = MutableStateFlow<List<FAQ>>(emptyList())
    val faqList = _faqList.asStateFlow()

    val faqFilterTypeList = listOf("General", "Account", "Service", "Payment")

    fun loadFAQList() {
        viewModelScope.launch {
            _faqList.emit(userService.getFAQList())
        }
    }

    fun filterFAQList(posType: Int, query: String) {
        viewModelScope.launch {
            _faqList.emit(userService.getFAQList().filter {
                it.type.toFAQType() == FAQType.entries.toTypedArray()[posType]
            }.filter {
                it.title.contains(query) || it.content.contains(query)
            })
        }
    }

}


